package com.example.exercices.ex12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.exercices.ex12.entities.Order;
import com.example.exercices.ex12.entities.OrderStatus;
import com.example.exercices.ex12.services.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
@Profile("ex12")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public Mono<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
    
    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
    
    @PutMapping("/{id}")
    public Mono<Order> updateOrderStatus(@PathVariable String id, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }
    
    @DeleteMapping("/{id}")
    public Mono<Void> deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
    
    @GetMapping("/search")
    public Flux<Order> searchByStatus(@RequestParam String status) {
        return orderService.searchByStatus(status);
    }
    
    @GetMapping("/paged")
    public Flux<Order> getPagedOrders(@RequestParam int page, @RequestParam int size) {
        return orderService.getPagedOrders(page, size);
    }
}