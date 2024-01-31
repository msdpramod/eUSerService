package org.commerceproject.euserservice.Controller;

import org.commerceproject.euserservice.DTOs.RoleRequestDTO;
import org.commerceproject.euserservice.DTOs.RoleResponseDTO;
import org.commerceproject.euserservice.Models.Roles;
import org.commerceproject.euserservice.Service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO){
        Roles role = roleService.createRole(roleRequestDTO.getName());
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setName(role.getName());
        return new ResponseEntity<>(roleResponseDTO, HttpStatus.CREATED);

    }
}
