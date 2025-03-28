package com.example.springtodosubject.todo.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteTodoRequest(
        @NotNull(message = "일정 관리 비밀번호가 반드시 포함되어야 합니다.")
        String password
) {
}
