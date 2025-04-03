package com.a5ur4.goldengains.repository;

import java.util.Optional;

import com.a5ur4.goldengains.entity.Music;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findById(Long id);
    Optional<Music> findByGenre(String genre);
    Optional<Music> findByArtist(String artist);
    Optional<Music> findByTitle(String title);

    Boolean existsByGenre(String genre);
}
