package com.a5ur4.goldengains.dtos;

public record PersonalDataDTO(
    Long id,
    String name,
    String birthday,
    String phone,
    double weight,
    double height,
    String country,
    Long user
) {
}
