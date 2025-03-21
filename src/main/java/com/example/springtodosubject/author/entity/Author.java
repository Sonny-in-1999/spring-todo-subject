package com.example.springtodosubject.author.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
@AllArgsConstructor
public class Author {

    // author_id
    private Long authorId;

    // name
    private String name;

    // email
    private String email;

    // created_at
    private Timestamp createdAt;

    // updated_at
    private Timestamp updatedAt;
}
