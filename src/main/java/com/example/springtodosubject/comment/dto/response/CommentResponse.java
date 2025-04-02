package com.example.springtodosubject.comment.dto.response;

import com.example.springtodosubject.comment.entity.Comment;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record CommentResponse(
        Long commentId,
        String writerName,
        String content,
        String createdAt,
        String updatedAt
) {
    // Entity -> Response DTO
    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .writerName(comment.getAuthor().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .updatedAt(comment.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초")))
                .build();
    }
}
