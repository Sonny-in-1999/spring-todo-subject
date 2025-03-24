package com.example.springtodosubject.author.entity;

import com.example.springtodosubject.author.dto.AuthorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@AllArgsConstructor
public class Author {

    // author_id
    private Long authorId;

    // name
    private String name;

    // email
    private String email;

    // created_at
    private Timestamp createdAt;

    // updated_at
    private Timestamp updatedAt;

    // 응답 DTO로 변환
    public AuthorResponse convertToDTO() {
        return AuthorResponse.builder()
                .authorId(authorId)
                .name(name)
                .email(email)
                .createdAt(createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(updatedAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
