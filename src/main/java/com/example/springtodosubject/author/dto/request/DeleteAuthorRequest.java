package com.example.springtodosubject.author.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteAuthorRequest(
        @NotNull(message = "비밀번호가 반드시 포함되어야 합니다.")
        String password
) {
}
