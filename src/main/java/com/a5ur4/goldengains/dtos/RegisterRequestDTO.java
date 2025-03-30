package com.a5ur4.goldengains.dtos;

public record RegisterRequestDTO(
        String username,
        String email,
        String password
) {
}
