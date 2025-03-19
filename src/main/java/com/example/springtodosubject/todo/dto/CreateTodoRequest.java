package com.example.springtodosubject.todo.dto;

import com.example.springtodosubject.todo.entity.Todo;

import java.sql.Date;

public record CreateTodoRequest(
        Long todoId,
        String title,
        String writer,
        String password
) {

    // Entity로 변환
    public Todo convertToEntity() {
        Date now = new Date(System.currentTimeMillis()); // 현재시간
        return Todo.builder()
                .todoId(todoId)
                .title(title)
                .writer(writer)
                .password(password)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
