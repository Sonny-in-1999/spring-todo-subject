package com.example.springtodosubject.todo.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record TodoResponse(
        Long todoId,
        String title,
        String writer,
        Date createdAt,
        Date updatedAt
) {
}
