package com.example.springtodosubject.todo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(
        @Size(max = 200, message = "일정은 200자 이내로 작성되어야 합니다.")
        @NotNull(message = "일정 항목의 정보는 반드시 포함되어야 합니다.")
        String title,

        @NotNull(message = "작성자 정보가 반드시 포함되어야 합니다.")
        Long authorId,

        @NotNull(message = "일정 관리 비밀번호가 반드시 포함되어야 합니다.")
        String password
) {
}
