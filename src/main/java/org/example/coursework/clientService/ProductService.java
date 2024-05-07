package org.example.coursework.clientService;

import jakarta.transaction.Transactional;
import org.example.coursework.clientService.repo.ProductRepository;
import org.example.coursework.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
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
    public Page<Product> findProductsByPage(int pageNumber, int pageSize, String searchValue) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findItemsByNameContainingIgnoreCase(searchValue, pageable);
    }
    @Transactional
    public Page<Product> getSortedProduct(int pageNumber, int pageSize, boolean value, String searchValue){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return value ? productRepository.findProductsByNameContainingIgnoreCaseSortedByCost(searchValue, pageable)
                : productRepository.findProductsByNameContainingIgnoreCaseSortedByCostDesc(searchValue, pageable);
    }
    @Transactional
    public long getTotalProductCount(String searchValue) {
        return productRepository.countProductsByNameContainingIgnoreCase(searchValue);
    }
}
