package com.example.springtodosubject.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
