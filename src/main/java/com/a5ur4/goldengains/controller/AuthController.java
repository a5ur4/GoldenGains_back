package com.a5ur4.goldengains.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.a5ur4.goldengains.dtos.LoginRequestDTO;
import com.a5ur4.goldengains.dtos.RegisterRequestDTO;
import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.UserRepository;
import com.a5ur4.goldengains.security.TokenService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            var user = userRepository.findByEmail(loginRequest.email());
            if (user.isPresent() && passwordEncoder.matches(loginRequest.password(), user.get().getPassword())) {
                String token = tokenService.generateToken(user.get());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            if (userRepository.existsByEmail(registerRequest.email())) {
                return ResponseEntity.status(400).body("Email already in use");
            }
            var user = new User();
            user.setUsername(registerRequest.username());
            user.setEmail(registerRequest.email());
            user.setPassword(passwordEncoder.encode(registerRequest.password()));
            userRepository.save(user);
            return ResponseEntity.status(201).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
