package com.a5ur4.goldengains.dtos;

public record MusicDTO(
    Long id,
    String genre,
    String artist,
    String song_name,
    String link,
    Long user
) {
}
