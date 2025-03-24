package com.a5ur4.goldengains.dtos;

import java.util.List;

public record UsersDTO(
    Long id,
    String username,
    String email,
    String password,
    String role,
    List<Long> lifts,
    List<Long> posts,
    Long music
) {
}
