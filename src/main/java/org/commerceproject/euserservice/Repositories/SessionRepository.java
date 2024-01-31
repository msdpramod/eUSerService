package org.commerceproject.euserservice.Repositories;


import org.commerceproject.euserservice.Models.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, UUID> {

    Optional<Sessions> findAllByTokenAndUser_Id(String Token, UUID userId);
}
