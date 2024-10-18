package org.example.coursework.database.repo;

import jakarta.transaction.Transactional;
import org.example.coursework.database.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
