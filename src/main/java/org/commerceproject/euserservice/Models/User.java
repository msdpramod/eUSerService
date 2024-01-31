package org.commerceproject.euserservice.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter@Setter @Entity
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany
    private Set<Roles> roles= new HashSet<>();

}
