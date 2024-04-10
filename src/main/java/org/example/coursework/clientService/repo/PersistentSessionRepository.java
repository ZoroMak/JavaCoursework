package org.example.coursework.clientService.repo;

import org.example.coursework.model.PersistentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistentSessionRepository extends JpaRepository<PersistentSession, Integer> {
}
