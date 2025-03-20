package com.example.springtodosubject.todo.dto;

import com.example.springtodosubject.todo.entity.Todo;

public record CreateTodoRequest(
        String title,
        String writer,
        String password
) {

    // Entity로 변환
    public Todo convertToEntity() {
        return Todo.builder()
                .title(title)
                .writer(writer)
                .password(password)
                .build();
    }
}
