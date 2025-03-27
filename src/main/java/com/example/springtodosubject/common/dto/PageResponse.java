package com.example.springtodosubject.common.dto;

import lombok.Builder;

@Builder
public record PageResponse<T>(
        T data,
        int currentPage,
        int totalPage,
        long totalSize
) {
}
