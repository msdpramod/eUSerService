package org.commerceproject.euserservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.commerceproject.euserservice.Models.Roles;
import org.commerceproject.euserservice.Models.User;

import java.util.HashSet;
import java.util.Set;

@Getter@Setter
public class UserDTO {
    private String email;
    private Set<Roles> roles=new HashSet<>();
    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
