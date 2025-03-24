package com.a5ur4.goldengains.repository;

import com.a5ur4.goldengains.entity.PersonalData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    Optional<PersonalData> findByUserId(Long userId);
    Optional<PersonalData> findById(Long id);
    Optional<PersonalData> findByName(String name);
    Optional<PersonalData> findByPhone(String phone);

    void deleteByUserId(Long userId);
}
