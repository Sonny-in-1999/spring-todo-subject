package com.example.springtodosubject.author.entity;

import com.example.springtodosubject.author.dto.AuthorResponse;
import com.example.springtodosubject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Table(name = "author")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author extends BaseEntity {

    // author_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    // name
    private String name;

    // email
    private String email;


    // 응답 DTO로 변환
    public AuthorResponse convertToDTO() {
        return AuthorResponse.builder()
                .authorId(authorId)
                .name(name)
                .email(email)
                .createdAt(getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
