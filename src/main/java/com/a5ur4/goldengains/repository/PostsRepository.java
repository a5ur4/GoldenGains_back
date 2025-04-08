package com.a5ur4.goldengains.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a5ur4.goldengains.entity.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findById(Long id);
    Optional<Posts> findByTitle(String title);
    Optional<Posts> findByUserId(Long userId);
    Optional<Posts> findByCategoryId(Long categoryId);

    Boolean existsByTitle(String title);
    Boolean existsByContent(String content);
}