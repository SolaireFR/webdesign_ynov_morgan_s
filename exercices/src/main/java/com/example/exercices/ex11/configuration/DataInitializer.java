package com.example.exercices.ex11.configuration;

import com.example.exercices.ex11.entities.User;
import com.example.exercices.ex11.entities.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Configuration
public class DataInitializer {

    // On n'utilise plus MongoProperties ici, on va juste tester la connexion
    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            System.out.println("🚀 Tentative d'initialisation des données...");
            
            User user1 = new User("Alice", "alice@example.com", true);
            User user2 = new User("Bob", "bob@example.com", true);

            userRepository.deleteAll()
                .thenMany(userRepository.saveAll(Flux.just(user1, user2)))
                .subscribe(
                    user -> System.out.println("✅ Utilisateur créé : " + user.getName()),
                    error -> System.err.println("❌ Erreur MongoDB : " + error.getMessage())
                );
        };
    }
}