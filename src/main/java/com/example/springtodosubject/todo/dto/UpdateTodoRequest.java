package com.example.springtodosubject.todo.dto;

public record UpdateTodoRequest(
        String title,
        Long authorId,
        String password
) {
}
