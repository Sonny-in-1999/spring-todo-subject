package com.example.springtodosubject.todo.dto;

import lombok.Builder;

@Builder
public record TodoPageResponse<T>(
        T data,
        int currentPage,
        int totalPage,
        long totalSize
) {
}
