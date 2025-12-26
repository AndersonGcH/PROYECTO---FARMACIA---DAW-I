package com.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())       // Desactiva CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()       // Todas las rutas públicas
            )
            .httpBasic(httpBasic -> httpBasic.disable())  // Desactiva HTTP Basic
            .formLogin(form -> form.disable())           // Desactiva login por formulario
            .logout(logout -> logout.disable());         // Desactiva logout

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Para encriptar passwords
    }
}
