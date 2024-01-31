package org.commerceproject.euserservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commerceproject.euserservice.Models.Roles;
import org.commerceproject.euserservice.Models.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter@Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private Set<Roles> roles=new HashSet<>();
    public static UserDTO from(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
