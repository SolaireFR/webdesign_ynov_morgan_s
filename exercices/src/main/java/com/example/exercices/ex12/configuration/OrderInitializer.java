package com.example.exercices.ex12.configuration;

import com.example.exercices.ex12.entities.Order;
import com.example.exercices.ex12.entities.OrderStatus;
import com.example.exercices.ex12.entities.repositories.OrderRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Configuration
@Profile("ex12")
public class OrderInitializer {

    // On n'utilise plus MongoProperties ici, on va juste tester la connexion
    @Bean
    public CommandLineRunner initOrders(OrderRepository orderRepository) {
        return args -> {
            System.out.println("🚀 Tentative d'initialisation des données...");
            
            Order order1 = new Order("Commande 1", 100.0, OrderStatus.PENDING);
            Order order2 = new Order("Commande 2", 200.0, OrderStatus.PENDING);

            orderRepository.deleteAll()
                .thenMany(orderRepository.saveAll(Flux.just(order1, order2)))
                .subscribe(
                    order -> System.out.println("✅ Commande créée : " + order),
                    error -> System.err.println("❌ Erreur MongoDB : " + error.getMessage())
                );
        };
    }
}
