package com.a5ur4.goldengains.repository;

import java.util.Optional;

import com.a5ur4.goldengains.entity.Lifts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LiftsRepository extends JpaRepository<Lifts, Long> {
    Optional<Lifts> findById(Long id);
    Optional<Lifts> findByName(String name);

    Boolean existsByName(String name);
}
