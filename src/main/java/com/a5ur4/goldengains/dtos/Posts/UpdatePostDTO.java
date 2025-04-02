package com.a5ur4.goldengains.dtos.Posts;

public record UpdatePostDTO(
    String title,
    String content,
    String image,
    Long user,
    Long category,
    Integer upvotes,
    Integer downvotes
) {
}
