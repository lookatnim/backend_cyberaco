package com.ecommerce.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecommerce.backend.repo.UserRepository;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("Loading user by username: " + username);
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        System.out.println("Creating BCryptPasswordEncoder bean");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.out.println("Creating AuthenticationManager bean");
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        System.out.println("Creating DaoAuthenticationProvider bean");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
