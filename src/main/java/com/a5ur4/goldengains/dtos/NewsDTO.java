package com.a5ur4.goldengains.dtos;

public record NewsDTO(
    Long id,
    String title,
    String content,
    String link,
    Long category
) {
}
