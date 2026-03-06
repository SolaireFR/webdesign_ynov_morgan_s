package com.example.exercices.ex13.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.exercices.ex13.entities.Product;
import com.example.exercices.ex13.entities.repositories.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Profile("ex13")
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(String id, Product productUpdated) {
        return productRepository.findById(id)
            .flatMap(product -> {
                product.setName(productUpdated.getName());
                product.setPrice(productUpdated.getPrice());
                product.setStock(productUpdated.getStock());
                return productRepository.save(product);
            });
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    public Flux<Product> searchByName(String name) {
        return productRepository.findAll()
            .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()));
    }

    public Mono<Product> reduceStock(String id, Integer quantity) {
        System.out.println("Reducing stock for product " + id + " by quantity " + quantity);
        return productRepository.findById(id)
            .flatMap(product -> {
                if (product.getStock() < quantity) {
                    return Mono.error(new IllegalArgumentException("Insufficient stock"));
                }
                product.setStock(product.getStock() - quantity);
                System.out.println("New stock for product " + id + ": " + product.getStock());
                return productRepository.save(product);
            });
    }
}
