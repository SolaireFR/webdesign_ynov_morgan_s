package com.example.exercices.ex12.entities;

import org.springframework.context.annotation.Profile;

@Profile("ex12")
public enum OrderStatus {
    PENDING,
    SHIPPED,
    DELIVERED
}
