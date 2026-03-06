package com.example.exercices.ex13.configuration;

import com.example.exercices.ex13.entities.Product;
import com.example.exercices.ex13.entities.repositories.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Configuration
@Profile("ex13")
public class ProductInitializer {

    // On n'utilise plus MongoProperties ici, on va juste tester la connexion
    @Bean
    public CommandLineRunner initProducts(ProductRepository productRepository) {
        return args -> {
            System.out.println("🚀 Tentative d'initialisation des données...");
            
            Product product1 = new Product("Produit 1", 100.0, 10);
            Product product2 = new Product("Produit 2", 200.0, 20);

            productRepository.deleteAll()
                .thenMany(productRepository.saveAll(Flux.just(product1, product2)))
                .subscribe(
                    order -> System.out.println("✅ Commande créée : " + order),
                    error -> System.err.println("❌ Erreur MongoDB : " + error.getMessage())
                );
        };
    }
}
