package org.commerceproject.euserservice.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

import org.commerceproject.euserservice.DTOs.UserDTO;
import org.commerceproject.euserservice.Exceptions.InvalidCredentialException;
import org.commerceproject.euserservice.Exceptions.InvalidTokenException;
import org.commerceproject.euserservice.Exceptions.UserNotFoundException;
import org.commerceproject.euserservice.Mapper.UserEntityDTOMapper;
import org.commerceproject.euserservice.Models.SessionStatus;
import org.commerceproject.euserservice.Models.Sessions;
import org.commerceproject.euserservice.Models.User;
import org.commerceproject.euserservice.Repositories.SessionRepository;
import org.commerceproject.euserservice.Repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDate;
import java.util.*;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<List<Sessions>> getAllSession(){
        List<Sessions> sessions = sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserDTO> login(String email, String password) {
        //Get user details from DB
        //
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User for the given email id does not exist");
        }
        User user = userOptional.get();
        //Verify the user password given at the time of login
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialException("Invalid Credentials");
        }
        //token generation

        //String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        //start adding the claims
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.getId());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJWT) // added the claims
                .signWith(key, alg) // added the algo and key
                .compact(); //building the token


        //session creation
        Sessions session = new Sessions();
        session.setStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        session.setCreated_at(new Date());
        session.setExpires_at(new Date(LocalDate.now().plusDays(3).toEpochDay()));
        sessionRepository.save(session);


        //generating the response
        //UserEntityDTOMapper is a class that maps the UserEntity to UserDTO and vice versa
        UserDTO userDto = UserEntityDTOMapper.getUserDTOFromUserEntity(user);


        //setting up the headers
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    public ResponseEntity<Void> logout(String token, UUID userId) {
        // validations -> token exists, token is not expired, user exists else throw an exception
//        Optional<Sessions> sessionOptional = sessionRepository.findAllByTokenAndUser_Id(token, userId);
//        if (sessionOptional.isEmpty()) {
//            return null; //TODO throw exception here
//        }
//        Sessions session = sessionOptional.get();
//        session.setStatus(SessionStatus.INACTIVE);
//        sessionRepository.save(session);
//        return ResponseEntity.ok().build();
        Optional<Sessions> sessionOptional = sessionRepository.findAllByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty()) {
            throw new InvalidTokenException("Token not found for user");
        }

        // TODO: Check if the token is not expired
//        if (!validate(token,userId).equals(SessionStatus.ACTIVE)) {
//            throw new InvalidTokenException("Token has expired or is invalid");
//        }

        Sessions session = sessionOptional.get();
        session.setStatus(SessionStatus.INACTIVE);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }
    public UserDTO signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return UserDTO.from(savedUser);
    }

    public SessionStatus validate(String token, UUID userId) throws Exception {
        //TODO check expiry // Jwts Parser -> parse the encoded JWT token to read the claims
        // Step 1: Parse the token
//        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
//        SecretKey key = alg.key().build(); // generating the secret key
//        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
//        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getEncoded(), sa.getJcaName());
//        JwtParser claims = Jwts.parser()
//                .verifyWith(secretKeySpec)
//                .build();
//        try {
//            claims.parse(token);
//        } catch (Exception e) {
//            throw new Exception("Could not verify JWT token integrity!", e);
//        }
//        // Step 2: Check token expiry
//        Date expiration = claims.getBody().getExpiration();
//        if (expiration.before(new Date())) {
//            throw new InvalidTokenException("Token has expired");
//        }

        //verifying from DB if session exists
        Optional<Sessions> sessionOptional = sessionRepository.findAllByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty() || sessionOptional.get().getStatus().equals(SessionStatus.INACTIVE)) {
            throw new InvalidTokenException("token is invalid");
        }
        return SessionStatus.ACTIVE;
    }
}
