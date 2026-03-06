package com.example.exercices.ex12.entities.repositories;

import com.example.exercices.ex12.entities.Order;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Profile("ex12")
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findAllByCustomerName(String customerName);
    Flux<Order> findByStatus(String status);
    Flux<Order> findAllBy(Pageable pageable);
}
