package com.a5ur4.goldengains.dtos;

import java.sql.Date;

public record PostsDTO(
    Long id,
    String title,
    String content,
    String image,
    Date created_at,
    Integer upvotes,
    Integer downvotes,
    Long user,
    Long category
) {
}