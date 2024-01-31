package org.commerceproject.euserservice.Controller;

import org.commerceproject.euserservice.DTOs.SetRolesRequestDTO;
import org.commerceproject.euserservice.DTOs.UserDTO;
import org.commerceproject.euserservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID userid){
        UserDTO userDTO = userService.getUserById(userid);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") UUID userId, @RequestBody SetRolesRequestDTO setRolesRequestDTO){
        UserDTO userDTO = userService.setUserRoles(userId,setRolesRequestDTO.getRoleIds());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
