package com.example.exercices.ex12.services;

import com.example.exercices.ex12.entities.Order;
import com.example.exercices.ex12.entities.OrderStatus;
import com.example.exercices.ex12.entities.repositories.OrderRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Profile("ex12")
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Mono<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }
    
    public Mono<Order> createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());        
        return orderRepository.save(order);
    }
    
    public Mono<Order> updateOrderStatus(String id, OrderStatus status) {
        return orderRepository.findById(id)
                .flatMap(order -> {
                    order.setStatus(status);
                    return orderRepository.save(order);
                });
    }

    public Mono<Void> deleteOrder(String id) {
        return orderRepository.deleteById(id);
    }

    public Flux<Order> searchByStatus(String status) {
        System.out.println(orderRepository.findByStatus(status));
        return orderRepository.findByStatus(status);
    }

    public Flux<Order> getPagedOrders(int page, int size) {
        return orderRepository.findAllBy(PageRequest.of(page, size));
    }
}
