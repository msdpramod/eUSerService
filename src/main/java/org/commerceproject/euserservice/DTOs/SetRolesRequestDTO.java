package org.commerceproject.euserservice.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter@Setter
public class SetRolesRequestDTO {
    private List<UUID> roleIds;
}
