package com.ecommerce.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.LoginUserDto;
import com.ecommerce.backend.dto.RegisterUserDto;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.response.LoginResponse;
import com.ecommerce.backend.services.AuthenticationService;
import com.ecommerce.backend.services.JwtService;

import jakarta.annotation.PostConstruct;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    @PostConstruct 
    public void initRolesAndUsers() {
        authenticationService.initRolesAndUsers();
    }

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(authenticatedUser.getId(), authenticatedUser.getFirstName(), authenticatedUser.getLastName(), authenticatedUser.getUsername(), authenticatedUser.getEmail(), authenticatedUser.getMobileNumber(),authenticatedUser.getUserRole(), jwtToken , jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
