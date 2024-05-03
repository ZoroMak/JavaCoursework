package org.example.coursework.clientService.repo;

import org.example.coursework.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Product findById(int id);
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Page<Product> findAll(Pageable pageable);
    long count();
}
