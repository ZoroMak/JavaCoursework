package org.example.coursework.database.repo;

import org.example.coursework.database.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer>{
    @Transactional(propagation = Propagation.REQUIRED)
    Optional<ApplicationUser> findByEmail(String email);
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    boolean existsByEmail(String email);
}
