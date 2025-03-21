package com.example.springtodosubject.todo.dto;

import lombok.Builder;

@Builder
public record TodoResponse(
        Long todoId,
        String title,
        String createdAt,
        String updatedAt
) {
}
