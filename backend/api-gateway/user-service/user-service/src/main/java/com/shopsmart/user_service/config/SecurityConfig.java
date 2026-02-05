package com.shopsmart.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Ye password ko encrypt karne ki machine hai
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Ye rules set karta hai ki kaun andar aa sakta hai
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Testing ke liye form security disable ki
                .authorizeHttpRequests(auth -> auth
                        // Sirf in URLs ko bina login ke allow karo
                        .requestMatchers("/api/users/register", "/api/users/test").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}