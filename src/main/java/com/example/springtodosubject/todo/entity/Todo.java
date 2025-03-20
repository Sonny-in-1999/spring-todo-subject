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

    // writer
    @Setter
    private String writer;

    // password
    private String password;

    // created_at
    private Timestamp createdAt;

    // updated_at
    @Setter
    private Timestamp updatedAt;

    public TodoResponse convertToDTO() {
        return TodoResponse.builder()
                .todoId(todoId)
                .title(title)
                .writer(writer)
                .createdAt(createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(updatedAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
