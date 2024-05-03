package org.example.coursework.clientService.repo;

import org.example.coursework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional(propagation = Propagation.REQUIRED)
    Optional<User> findByEmail(String email);
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    boolean existsByEmail(String email);
}
