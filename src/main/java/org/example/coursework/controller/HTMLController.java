package org.example.coursework.controller;

import org.example.coursework.clientService.ProductService;
import org.example.coursework.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;


@RestController
public class HTMLController {

    private final ProductService productService;

    @Autowired
    public HTMLController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    @GetMapping("/regist")
    public ModelAndView getRegistPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("regist.html");
        return modelAndView;
    }

    @GetMapping("/basket")
    public ModelAndView getBasketPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("basket.html");
        return modelAndView;
    }

    @GetMapping("/static/css/{name}")
    public byte[] getCSS(@PathVariable String name) throws IOException {
        Resource resource = new ClassPathResource("/webapp/css/" + name);
        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    @GetMapping("/static/img/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        Resource resource = new ClassPathResource("/webapp/img/" + name);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] imageBytes = inputStream.readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
    }

    @GetMapping("/static/js/{name}")
    public ResponseEntity<byte[]> getJS(@PathVariable String name) throws IOException {
        Resource resource = new ClassPathResource("/webapp/js/" + name);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] jsBytes = inputStream.readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("application/javascript"));
            return new ResponseEntity<>(jsBytes, headers, HttpStatus.OK);
        }
    }



    @PostMapping(value = "/product")
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}