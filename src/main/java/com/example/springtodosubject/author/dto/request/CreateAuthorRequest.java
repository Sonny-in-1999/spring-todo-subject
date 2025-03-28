package com.example.springtodosubject.author.dto.request;

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
        String email,

        @NotNull(message = "일정 관리 비밀번호가 반드시 포함되어야 합니다.")
        String password
) {

    // Entity로 변환
    public Author convertToEntity() {
        return Author.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
