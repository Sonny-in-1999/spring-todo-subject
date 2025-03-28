package com.example.springtodosubject.todo.dto.response;

import lombok.Builder;

@Builder
public record TodoResponse(
        Long todoId,
        String writerName,
        String writerEmail,
        String title,
        String createdAt,
        String updatedAt
) {
}
