package com.example.springtodosubject.author.dto;

public record UpdateAuthorRequest(
        String name,
        String email
) {
}
