package com.example.exercices.ex13.entities.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.exercices.ex13.entities.Product;

import reactor.core.publisher.Flux;

@Repository
@Profile("ex13")
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByPrice(Double price);
    Flux<Product> findAllByStock(Integer stock);
}
