package com.a5ur4.goldengains.dtos.Categories;

import java.util.List;

public record CategoriesDTO(
    Long id,
    String name,
    String description,
    List<Long> posts
) {
}
