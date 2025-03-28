package com.example.springtodosubject.author.repository;

import com.example.springtodosubject.author.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(attributePaths = "todoList")
    Optional<Author> findById(Long authorId);
}
