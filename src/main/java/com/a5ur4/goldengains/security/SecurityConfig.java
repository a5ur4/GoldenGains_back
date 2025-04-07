package com.a5ur4.goldengains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configure(http)) // Permite CORS
            .csrf(csrf -> csrf.disable()) // Desativa CSRF para APIs REST
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Permissões de acesso usuário
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/users/get_all", "/users/get_by_id/**", "/users/get_by_email/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/users/update/**", "/users/update_role/**", "/users/delete/**").hasRole("ADMIN")

                // Permissões de acesso personal data
                .requestMatchers("/personal-data/**").permitAll() 

                // Permissões de acesso categoria
                .requestMatchers("/categories/get_all", "/categories/get_by_id/**").permitAll()
                .requestMatchers("/categories/create", "/categories/update/**", "/categories/delete/**").hasAnyRole("USER", "ADMIN")
                
                // Permissões de acesso posts
                .requestMatchers("/posts/get_all", "/posts/get_by_id/**").permitAll()
                .requestMatchers("/posts/create", "/posts/update/**", "/posts/delete/**").hasAnyRole("USER", "ADMIN")

                // Permissões de acesso news
                .requestMatchers("/news/get_all", "/news/get_by_id/**").permitAll()
                .requestMatchers("/news/create", "/news/update/**", "/news/delete/**").hasAnyRole("USER", "ADMIN")

                // Permissões de acesso music
                .requestMatchers("/music/get_all", "/music/get_by_id/**").permitAll()
                .requestMatchers("/music/create", "/music/update/**", "/music/delete/**").hasAnyRole("USER", "ADMIN")

                // Permissões de acesso lifts
                .requestMatchers("/lifts/get_all", "/lifts/get_by_id/**").permitAll()
                .requestMatchers("/lifts/create", "/lifts/update/**", "/lifts/delete/**").hasAnyRole("USER", "ADMIN")

                // Permissões de acesso ranks
                .requestMatchers("/ranks/get_all", "/ranks/get_by_id/**").permitAll()
                .requestMatchers("/ranks/create", "/ranks/update/**", "/ranks/delete/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); // Filtro JWT

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
