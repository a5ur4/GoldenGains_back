package com.a5ur4.goldengains.dtos;

public record LiftsDTO(
    Long id,
    String name,
    double weight,
    int reps,
    int sets,
    Long user
) {
}