package com.example.springtodosubject.author.dto;

import com.example.springtodosubject.author.entity.Author;

public record CreateAuthorRequest(
        String name,
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
