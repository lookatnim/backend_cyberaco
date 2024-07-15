package com.ecommerce.backend.services;

import java.util.HashSet;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.dto.LoginUserDto;
import com.ecommerce.backend.dto.RegisterUserDto;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repo.UserRepository;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto registerUserDto) {
        // Logging the received input data
        System.out.println("Signup input received: " + registerUserDto.toString());

        User user = new User()        
            .setFirstName(registerUserDto.getFirstName())
            .setLastName(registerUserDto.getLastName())
            .setUserName(registerUserDto.getUserName())
            .setEmail(registerUserDto.getEmail())
            .setMobileNumber(registerUserDto.getMobileNumber())
            .setPassword(passwordEncoder.encode(registerUserDto.getPassword()))
            .setUserRole(2);

        // Logging the user object being created
        System.out.println("User object created: " + user.toString());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    @Transactional
    public void initRolesAndUsers() {
        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Admin");
        adminUser.setUserName("admin");
        adminUser.setEmail("admin@cybe.com");
        adminUser.setMobileNumber("1234567890");
        adminUser.setPassword(passwordEncoder.encode("12345"));
        adminUser.setUserRole(1);
        userRepository.save(adminUser);
        
        User regularUser = new User();
        regularUser.setFirstName("Tharindu");
        regularUser.setLastName("Nimesh");
        regularUser.setUserName("user");
        regularUser.setEmail("user@cybe.com");
        regularUser.setMobileNumber("1234567890");
        regularUser.setPassword(passwordEncoder.encode("12345"));
        regularUser.setUserRole(2);
        userRepository.save(regularUser);
    }
}

