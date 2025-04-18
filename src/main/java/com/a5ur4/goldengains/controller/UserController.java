package com.a5ur4.goldengains.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.a5ur4.goldengains.dtos.User.RoleUpdateDTO;
import com.a5ur4.goldengains.dtos.User.UserDTO;
import com.a5ur4.goldengains.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = this.userService.getUser(id)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_email")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            UserDTO user = this.userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        try {
            UserDTO updatedUser = this.userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update_role/{id}")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody @Valid RoleUpdateDTO roleUpdateDTO) {
        try {
            UserDTO updatedUser = this.userService.updaterUserRole(id, roleUpdateDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deletedUser = this.userService.deleteUser(id);
            if (deletedUser) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.status(500).body("User not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
