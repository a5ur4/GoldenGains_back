package com.a5ur4.goldengains.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.User.RoleUpdateDTO;
import com.a5ur4.goldengains.dtos.User.UserDTO;
import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String postUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User created successfully";
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .toList();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Optional<UserDTO> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToUserDTO);
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(this::convertToUserDTO);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        if (userDTO.username() != null) {
            user.setUsername(userDTO.username());
        }
        if (userDTO.email() != null) {
            user.setEmail(userDTO.email());
        }
        user.setRole(userDTO.role() != null ? userDTO.role() : "USER");
        userRepository.save(user);
        return convertToUserDTO(user);
    }

    public UserDTO updaterUserRole(Long id, RoleUpdateDTO roleUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    
        String role = roleUpdateDTO.role();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
    
        user.setRole(role);
        userRepository.save(user);
        return convertToUserDTO(user);
    }    

    public Boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
        return true;
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }
}
