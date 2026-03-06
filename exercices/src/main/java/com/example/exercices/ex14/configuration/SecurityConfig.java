package com.example.exercices.ex14.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@Profile("ex14")
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
                .pathMatchers("/public/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/rooms/**").permitAll()
                .pathMatchers(HttpMethod.POST, "/api/rooms/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/api/rooms/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint((exchange, ex) -> 
                    sendError(exchange, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Vous devez vous connecter pour accéder à cette ressource")
                )
                .and()
                .exceptionHandling()
                .accessDeniedHandler((exchange, ex) -> 
                    sendError(exchange, HttpStatus.FORBIDDEN, "FORBIDDEN", "Vous n'avez pas les droits nécessaires pour accéder à cette ressource")
                )
                .and()
                .csrf().disable()
                .build();
    }

    private static Mono<Void> sendError(org.springframework.web.server.ServerWebExchange exchange, HttpStatus status, String error, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String json = String.format("{\"error\":\"%s\",\"message\":\"%s\"}", error, message);
        byte[] bytes = json.getBytes();
        return exchange.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap(bytes)));
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("root")
                .password("root")
                .roles("ADMIN")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }
}
