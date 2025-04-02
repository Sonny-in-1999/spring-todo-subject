package com.example.springtodosubject.comment.dto.request;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.comment.entity.Comment;
import com.example.springtodosubject.todo.entity.Todo;

public record CreateCommentRequest(
        Long todoId,
        Long authorId,
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
