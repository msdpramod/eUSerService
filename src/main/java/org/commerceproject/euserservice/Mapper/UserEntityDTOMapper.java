package org.commerceproject.euserservice.Mapper;

import org.commerceproject.euserservice.DTOs.UserDTO;
import org.commerceproject.euserservice.Models.User;

public class UserEntityDTOMapper {
    public static UserDTO getUserDTOFromUserEntity(User user){
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}