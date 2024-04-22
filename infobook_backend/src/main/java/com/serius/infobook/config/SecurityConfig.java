package com.serius.infobook.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/v1/**" )) // ignoring csrf protect for all requests coming from "/api/v1/students"

                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/v1/user/signup").permitAll()
                                .requestMatchers("/api/v1/students/addStudent", "/api/v1/students/getStudent", "/api/v1/students/getAll", "/api/v1/students/fetchallbypaginationandsorting", "/api/v1/students/fetchallbypagination").hasRole("USER")
                                .requestMatchers("/api/v1/students/updateStudent", "/api/v1/students/deleteStudent").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults()); // Use HTTP Basic authentication with default settings
//                .requiresChannel(e -> e.anyRequest().requiresSecure());
        return http.build();


    }


    // Define a UserDetailsService bean for providing user details
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder() // Creates a user and automatically encodes the provided password using PasswordEncoderFactories.createDelegatingPasswordEncoder()
                .username("serius")
                .password("test1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user); // Create an InMemoryUserDetailsManager with the user details, which saves the given credentials in memory.
    }
}

/*
For Basic Authentication:

username: user
password: generated security password in the console

For Custom Basic Authentication

Define a UserDetailsService bean for providing user details
- Set Username
- Set Password
- Set Roles(if any)
- Then return the user object using InMemoryUserDetailsManager
 */