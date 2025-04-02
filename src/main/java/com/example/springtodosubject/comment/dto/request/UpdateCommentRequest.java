package com.example.springtodosubject.comment.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateCommentRequest(
        @NotNull(message = "댓글의 내용이 반드시 포함되어야합니다.")
        String content
) {
}
