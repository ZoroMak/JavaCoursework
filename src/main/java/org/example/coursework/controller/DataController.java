package org.example.coursework.controller;

import lombok.RequiredArgsConstructor;
import org.example.coursework.clientService.CustomerService;
import org.example.coursework.clientService.OrderService;
import org.example.coursework.clientService.ProductService;
import org.example.coursework.model.Customer;
import org.example.coursework.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DataController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @PostMapping(value = "/createOrder")
    public ResponseEntity<Void> sendData(@ModelAttribute Customer customer){
        customerService.save(customer);
        orderService.createOrder(customer, customer.getShoppingCart());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getData")
    public ResponseEntity<Page<Product>> getData(@RequestParam("page_number") int page_number,
                                                 @RequestParam("countPerPage") int countPerPage,
                                                 @RequestParam("search_value") String searchValue){
        Page<Product> productList = productService.findProductsByPage(page_number, countPerPage, searchValue);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/getSortedData")
    public ResponseEntity<Page<Product>> getSortedData(@RequestParam("page_number") int page_number,
                                                       @RequestParam("countPerPage") int countPerPage,
                                                       @RequestParam boolean value,
                                                       @RequestParam("search_value") String searchValue){
        Page<Product> productList = productService.getSortedProduct(page_number, countPerPage, value, searchValue);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/getLength")
    public ResponseEntity<Long> getLength(@RequestParam("search_value") String searchValue){
        System.out.println(searchValue);
        Long length = productService.getTotalProductCount(searchValue);
        return new ResponseEntity<>(length, HttpStatus.OK);
    }
}
