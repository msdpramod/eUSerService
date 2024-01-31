package org.commerceproject.euserservice.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter@Entity
public class Roles extends BaseModel{
    private String name;

}
