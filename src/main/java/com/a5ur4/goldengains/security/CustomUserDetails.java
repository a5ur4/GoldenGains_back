package com.a5ur4.goldengains.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.UserRepository;

@Component
public class CustomUserDetails implements UserDetailsService {
    
    private final UserRepository repository;

    // Injeta UserRepository corretamente pelo construtor
    public CustomUserDetails(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), new ArrayList<>()
        );
    }
}
