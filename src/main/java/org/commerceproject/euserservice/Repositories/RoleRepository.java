package org.commerceproject.euserservice.Repositories;


import org.commerceproject.euserservice.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
    <Optional> List<Roles> findAllByIdIn(List<UUID> releIds);


}
