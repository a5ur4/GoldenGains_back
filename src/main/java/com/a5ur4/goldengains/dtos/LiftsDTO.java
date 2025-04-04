package com.a5ur4.goldengains.dtos;

public record LiftsDTO(
    Long id,
    String name,
    Double weight,
    Integer reps,
    Integer sets,
    Long user
) {
}