package com.a5ur4.goldengains.repository;

import java.util.Optional;

import com.a5ur4.goldengains.entity.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findById(Long id);
    Optional<Categories> findByName(String name);

    Boolean existsByName(String name);
}
