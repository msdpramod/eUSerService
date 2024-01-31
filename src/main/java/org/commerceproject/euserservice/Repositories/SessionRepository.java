package org.commerceproject.euserservice.Repositories;


import org.commerceproject.euserservice.Models.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, Long> {

    Optional<SessionRepository> findAllByTokenAndUser_Id(String Token, Long userId);
}
