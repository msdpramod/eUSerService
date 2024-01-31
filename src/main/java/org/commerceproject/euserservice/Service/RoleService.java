package org.commerceproject.euserservice.Service;

import org.commerceproject.euserservice.Models.Roles;
import org.commerceproject.euserservice.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Roles createRole(String name){
        Roles role = new Roles();
        role.setName(name);
        return roleRepository.save(role);
    }
}
