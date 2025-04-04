package com.a5ur4.goldengains.dtos;

import java.math.BigDecimal;

public record LiftsDTO(
    Long id,
    String name,
    BigDecimal weight,
    Integer reps,
    Integer sets,
    Long user
) {
}