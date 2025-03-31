package com.example.springtodosubject.todo.entity;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.dto.response.TodoResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Table(name = "todo")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    // todo_id(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    // title
    @Column(name = "title", nullable = false)
    private String title;

    // author_id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    // password
    @Column(name = "password", nullable = false)
    private String password;


    public void update(String title) {
        this.title = title;
    }

    // 응답 DTO로 변환
    public TodoResponse convertToDTO() {
        return TodoResponse.builder()
                .todoId(todoId)
                .writerName(author.getName())
                .writerEmail(author.getEmail())
                .title(title)
                .createdAt(getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
