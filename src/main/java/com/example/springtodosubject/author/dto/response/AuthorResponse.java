package com.example.springtodosubject.author.dto.response;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.todo.dto.response.TodoResponse;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
public record AuthorResponse(
        Long authorId,
        String name,
        String email,
        String createdAt,
        String updatedAt,
        List<TodoResponse> todoList
) {

    // Entity -> Response DTO
    public static AuthorResponse of(Author author) {
        return AuthorResponse.builder()
                .authorId(author.getAuthorId())
                .name(author.getName())
                .email(author.getEmail())
                .createdAt(author.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(author.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .todoList(author.getTodoList().stream().map(TodoResponse::of).toList())
                .build();
    }
}
