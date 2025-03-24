package com.example.springtodosubject.todo.entity;

import com.example.springtodosubject.todo.dto.TodoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@AllArgsConstructor
public class Todo {

    // todo_id(pk)
    private Long todoId;

    // title
    @Setter
    private String title;

    // author_Id
    private Long authorId;

    // password
    private String password;

    // created_at
    private Timestamp createdAt;

    // updated_at
    @Setter
    private Timestamp updatedAt;

    // 응답 DTO로 변환
    public TodoResponse convertToDTO() {
        return TodoResponse.builder()
                .todoId(todoId)
                .title(title)
                .createdAt(createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(updatedAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
