package com.example.springtodosubject.author.entity;

import com.example.springtodosubject.author.dto.response.AuthorResponse;
import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.entity.Todo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "name")
    @NotNull
    private String name;

    // email
    @Column(name = "email")
    @NotNull
    private String email;

    // password
    @Column(name = "password")
    @NotNull
    private String password;

    // 일정 일대다 매핑
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todoList = new ArrayList<>();

    // 작성자 정보 수정
    public void update(UpdateAuthorRequest request) {
        if (!request.password().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }

    // 응답 DTO로 변환
    public AuthorResponse convertToDTO() {
        return AuthorResponse.builder()
                .authorId(authorId)
                .name(name)
                .email(email)
                .createdAt(getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .todoList(todoList.stream().map(Todo::convertToDTO).toList())
                .build();
    }
}
