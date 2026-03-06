package com.example.exercice11.configuration;

import com.example.exercice11.entities.User;
import com.example.exercice11.entities.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@Configuration
public class DataInitializer {
    // @Autowired
    // private MongoProperties mongoProperties;

    // @Bean
    // public CommandLineRunner checkConfig() {
    //     // ON FORCE LES PARAMÈTRES ICI
    //     System.setProperty("spring.data.mongodb.host", "localhost");
    //     System.setProperty("spring.data.mongodb.port", "27017");
    //     System.setProperty("spring.data.mongodb.database", "exercice11");
    //     System.setProperty("spring.data.mongodb.username", "root");
    //     System.setProperty("spring.data.mongodb.password", "EXO11hjkl");
    //     System.setProperty("spring.data.mongodb.authentication-database", "admin");
    //     return args -> {
    //         System.out.println("--- CONFIGURATION MONGODB DETECTEE ---");
    //         System.out.println("Host: " + mongoProperties.getHost());
    //         System.out.println("Port: " + mongoProperties.getPort());
    //         System.out.println("Database: " + mongoProperties.getDatabase());
    //         System.out.println("Username: " + mongoProperties.getUsername());
    //         System.out.println("Authentication Database: " + mongoProperties.getAuthenticationDatabase());
    //         System.out.println("--------------------------------------");
    // };

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