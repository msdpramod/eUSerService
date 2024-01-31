package org.commerceproject.euserservice.Service;

import org.commerceproject.euserservice.DTOs.UserDTO;
import org.commerceproject.euserservice.Models.Roles;
import org.commerceproject.euserservice.Models.User;
import org.commerceproject.euserservice.Repositories.RoleRepository;
import org.commerceproject.euserservice.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserById(UUID userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            return null;
        }
        return UserDTO.from(userOptional.get());

    }
    public UserDTO setUserRoles(UUID userId, List<UUID> roleId){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Roles> rolesOptional = roleRepository.findAllByIdIn(roleId);
        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        user.setRoles(Set.copyOf(rolesOptional));
        userRepository.save(user);
        return UserDTO.from(user);

    }
}
