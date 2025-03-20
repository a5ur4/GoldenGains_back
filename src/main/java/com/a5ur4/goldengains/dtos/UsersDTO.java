package com.a5ur4.goldengains.dtos;

import java.time.LocalDate;
import java.util.List;

public record UsersDTO(
    Long id,
    String name,
    String username,
    String email,
    String password,
    LocalDate birthday,
    String phone,
    Double weight,
    Double height,
    String country,
    List<Long> lifts,
    List<Long> posts,
    Long music
) {
}
