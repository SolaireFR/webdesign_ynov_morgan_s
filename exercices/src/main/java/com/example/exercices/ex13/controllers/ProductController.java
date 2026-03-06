package com.example.exercices.ex13.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercices.ex13.entities.Product;
import com.example.exercices.ex13.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@Profile("ex13")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}/buy")
    public Mono<Product> reduceStock(@PathVariable String id, @RequestParam Integer quantity) {
        return productService.reduceStock(id, quantity);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product productUpdated) {
        return productService.updateProduct(id, productUpdated);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public Flux<Product> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }
}
