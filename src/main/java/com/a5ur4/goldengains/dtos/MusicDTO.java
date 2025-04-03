package com.a5ur4.goldengains.dtos;

public record MusicDTO(
    Long id,
    String genre,
    String artist,
    String title,
    String link,
    Long user
) {
}
