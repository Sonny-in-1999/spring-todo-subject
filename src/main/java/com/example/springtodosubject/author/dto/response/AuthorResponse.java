package com.example.springtodosubject.author.dto.response;

import com.example.springtodosubject.todo.dto.response.TodoResponse;
import lombok.Builder;

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
}
