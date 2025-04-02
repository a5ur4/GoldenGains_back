package com.a5ur4.goldengains.dtos.User;

public record RegisterRequestDTO(
        String username,
        String email,
        String password
) {
}
