package com.example.springtodosubject.todo.dto;

public record UpdateTodoRequest(
        String title,
        String writer,
        String password
) {
}
