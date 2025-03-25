package com.a5ur4.goldengains.repository;

import com.a5ur4.goldengains.entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);
    Optional<Users> findByUsernameOrEmail(String username, String email);
    Optional<Users> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
