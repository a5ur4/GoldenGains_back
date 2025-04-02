package com.a5ur4.goldengains.dtos.Posts;

public record CreatePostDTO(
    String title,
    String content,
    String image,
    Long user,
    Long category
) {
}
