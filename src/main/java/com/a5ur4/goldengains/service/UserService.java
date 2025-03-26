package com.a5ur4.goldengains.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.UserDTO;
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

    // CREATE - Criar um novo usuário
    public UserDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha antes de salvar
        User savedUser = userRepository.save(user);
        return convertToUserDTO(savedUser);
    }

    // READ - Buscar todos os usuários
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .toList();
    }

    // READ - Buscar um usuário por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // READ - Buscar um usuário e retornar DTO
    public Optional<UserDTO> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToUserDTO);
    }

    // UPDATE - Atualizar dados de um usuário
    public UserDTO updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        // Atualiza a senha somente se um novo valor for fornecido
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setRole(updatedUser.getRole());
        existingUser.setProfilePic(updatedUser.getProfilePic());

        User savedUser = userRepository.save(existingUser);
        return convertToUserDTO(savedUser);
    }

    // DELETE - Deletar usuário por ID
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Conversão para DTO
    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }
}
