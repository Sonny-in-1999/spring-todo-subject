package com.example.springtodosubject.todo.dto.response;

import com.example.springtodosubject.todo.entity.Todo;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record TodoResponse(
        Long todoId,
        String writerName,
        String writerEmail,
        String title,
        String startDate,
        String endDate,
        String createdAt,
        String updatedAt
) {

    // Entity -> Response DTO
    public static TodoResponse of(Todo todo) {
        return TodoResponse.builder()
                .todoId(todo.getTodoId())
                .writerName(todo.getAuthor().getName())
                .writerEmail(todo.getAuthor().getEmail())
                .title(todo.getTitle())
                .startDate(todo.getStartDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")))
                .endDate(todo.getEndDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")))
                .createdAt(todo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(todo.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
