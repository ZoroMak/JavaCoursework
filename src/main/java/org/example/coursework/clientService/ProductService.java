package org.example.coursework.clientService;

import jakarta.transaction.Transactional;
import org.example.coursework.clientService.repo.ProductRepository;
import org.example.coursework.clientService.scheduler.WriteToFile;
import org.example.coursework.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Page<Product> findProductsByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    public long getTotalProductCount() {
        return productRepository.count();
    }
}
