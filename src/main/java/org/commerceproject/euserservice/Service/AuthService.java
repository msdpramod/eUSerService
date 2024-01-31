package org.commerceproject.euserservice.Service;

import org.commerceproject.euserservice.Repositories.RoleRepository;
import org.commerceproject.euserservice.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

}
