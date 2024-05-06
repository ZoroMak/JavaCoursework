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
    public Page<Product> findProductsByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }
    @Transactional
    public List<Product> getSortedProduct(int pageNumber, int pageSize, boolean value){
        List<Product> list = productRepository.findAll();

        if (list.isEmpty())
            return null;

        Collections.sort(list);

        if (!value){
            Collections.reverse(list);
        }

        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, list.size());

        return list.subList(startIndex, endIndex);
    }
    @Transactional
    public long getTotalProductCount() {
        return productRepository.count();
    }
}
