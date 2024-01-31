package org.commerceproject.euserservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequestDTO {
    private String email;
    private String password;
}
