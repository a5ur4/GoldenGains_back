package com.a5ur4.goldengains.dtos.Posts;

import java.time.LocalDateTime;

public record PostDTO(
    Long id,
    String title,
    String content,
    String image,
    LocalDateTime createdAt,
    Integer upvotes,
    Integer downvotes,
    Long user,
    Long category
) {
}
