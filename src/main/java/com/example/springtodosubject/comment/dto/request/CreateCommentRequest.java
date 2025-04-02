package com.example.springtodosubject.comment.dto.request;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.comment.entity.Comment;
import com.example.springtodosubject.todo.entity.Todo;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(
        @NotNull(message = "댓글의 내용이 반드시 포함되어야합니다.")
        String content
) {

    // Entity로 변환
    public Comment convertToEntity(Todo todo, Author author) {
        return Comment.builder()
                .content(content)
                .todo(todo)
                .author(author)
                .build();
    }

}
