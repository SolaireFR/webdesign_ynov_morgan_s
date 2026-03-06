package com.example.exercices.ex11.entities.repositories;

import com.example.exercices.ex11.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findByEmail(String email);
    Mono<User> findByName(String name);
}
