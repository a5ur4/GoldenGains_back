package com.a5ur4.goldengains.repository;

import java.util.Optional;

import com.a5ur4.goldengains.entity.Ranks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RanksRepository extends JpaRepository<Ranks, Long> {
    Optional<Ranks> findById(Long id);
    Optional<Ranks> findByPosition(Integer position);

    Boolean existsByPosition(Integer position);

}
