package com.example.springtodosubject.author.dto;

import lombok.Builder;

@Builder
public record AuthorResponse(
        Long authorId,
        String name,
        String email,
        String createdAt,
        String updatedAt
) {
}
