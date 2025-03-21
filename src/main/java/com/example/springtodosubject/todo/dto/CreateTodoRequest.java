package com.example.springtodosubject.todo.dto;

import com.example.springtodosubject.todo.entity.Todo;

public record CreateTodoRequest(
        String title,
        Long authorId,
        String password
) {

    // Entity로 변환
    public Todo convertToEntity() {
        return Todo.builder()
                .title(title)
                .authorId(authorId)
                .password(password)
                .build();
    }
}
