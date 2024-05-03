package org.example.coursework.clientService;

import jakarta.transaction.Transactional;
import org.example.coursework.clientService.repo.ProductRepository;
import org.example.coursework.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }
    @Transactional
    public Page<Product> findProductsByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }
    @Transactional
    public long getTotalProductCount() {
        return productRepository.count();
    }
}
