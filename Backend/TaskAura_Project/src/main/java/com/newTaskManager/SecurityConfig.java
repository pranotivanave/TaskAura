package com.newTaskManager;

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
	        http.csrf().disable()
	            .authorizeRequests()
	            .requestMatchers("/tasks/**").permitAll()
	            .requestMatchers("/api/users/register", "/api/users/login").permitAll() // Allow registration and login without authentication
	            .requestMatchers("/api/users/**").authenticated() // Secure other user-related endpoints
	            .anyRequest().authenticated() // Secure all other endpoints
	            .and()
	            .httpBasic(); // Use basic authentication

	        return http.build();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // Password encoder for hashing user passwords
	    }
	}