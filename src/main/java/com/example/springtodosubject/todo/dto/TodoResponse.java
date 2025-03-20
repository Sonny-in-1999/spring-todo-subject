package com.example.springtodosubject.todo.dto;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record TodoResponse(
        Long todoId,
        String title,
        String writer,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
