package org.commerceproject.euserservice.Repositories;


import org.commerceproject.euserservice.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {
    <Optional> List<Roles> findAllByIdIn(Long releIds);


}
