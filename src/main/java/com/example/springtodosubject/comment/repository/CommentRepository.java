package com.example.springtodosubject.comment.repository;

import com.example.springtodosubject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getByTodo_TodoId(Long todoId);
}
