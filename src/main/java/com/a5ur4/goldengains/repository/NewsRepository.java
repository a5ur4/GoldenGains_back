package com.a5ur4.goldengains.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a5ur4.goldengains.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);
    Optional<News> findByTitle(String title);
    Optional<News> findByCategoryId(Long categoryId);

    Boolean existsByTitle(String title);
}
