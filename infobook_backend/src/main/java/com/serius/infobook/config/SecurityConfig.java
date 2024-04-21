package com.serius.infobook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/v1/students/**")) // ignoring csrf protect for all requests coming from "/api/v1/students"
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/students/addStudent").permitAll() // this url is permitted or open
                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults()); // all others url need basic authentication

        return http.build();
    }
}

/*
For Basic Authentication:

username: user
password: generated security password in the console
 */