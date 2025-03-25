package com.example.springtodosubject.author.dto;

import com.example.springtodosubject.author.entity.Author;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateAuthorRequest(

        @NotNull(message = "이름이 반드시 포함되어야합니다.")
        String name,

        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "유효한 이메일 주소를 입력해주세요."
        )
        @NotNull(message = "이메일이 반드시 포함되어야합니다.")
        String email
) {

    // Entity로 변환
    public Author convertToEntity() {
        return Author.builder()
                .name(name)
                .email(email)
                .build();
    }
}
