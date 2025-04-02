package com.a5ur4.goldengains.dtos.News;

import java.util.List;

public record NewsDTO(
    Long id,
    String title,
    String content,
    String link,
    List<Long> categories
) {
}
