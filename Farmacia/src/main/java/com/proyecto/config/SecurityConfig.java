package com.proyecto.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.proyecto.service.UsuarioDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final UsuarioDetailsServiceImpl usuarioDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                // ---------- PUBLICOS ----------
                .requestMatchers(
                    "/api/usuarios/login-form",
                    "/api/usuarios/registro-form",
                    "/css/**",
                    "/js/**"
                ).permitAll()

                // ---------- ROLES ----------
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/gestion/**").hasAnyRole("ADMIN", "GESTOR")
                .requestMatchers("/transporte/**").hasAnyRole("ADMIN", "TRANSPORTISTA")

                .anyRequest().authenticated()
            )

            // ---------- LOGIN ----------
            .formLogin(form -> form
                .loginPage("/api/usuarios/login-form")
                .loginProcessingUrl("/login")   // 🔴 IMPORTANTE
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/api/usuarios/home", true)
                .failureUrl("/api/usuarios/login-form?error")
                .permitAll()
            )

            // ---------- LOGOUT ----------
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/api/usuarios/login-form?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
            .userDetailsService(usuarioDetailsService)
            .passwordEncoder(passwordEncoder());

        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
    

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors(cors -> {}) // 👈 HABILITA CORS EN SECURITY
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/api/usuarios/registro",
//                    "/api/usuarios/login",
//                    "/api/usuarios/recuperar-password"
//                ).permitAll()
//                .requestMatchers("/api/usuarios/**").hasAnyRole("USER", "ADMIN","GESTOR","TRANSPORTISTA")
//                .anyRequest().authenticated()
//            )
//            .httpBasic(httpBasic -> {});
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return source;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authBuilder
//            .userDetailsService(usuarioDetailsService)
//            .passwordEncoder(passwordEncoder());
//
//        return authBuilder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
