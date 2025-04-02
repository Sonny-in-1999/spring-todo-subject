package com.example.springtodosubject.comment.controller;

import com.example.springtodosubject.comment.dto.request.CreateCommentRequest;
import com.example.springtodosubject.comment.dto.request.UpdateCommentRequest;
import com.example.springtodosubject.comment.dto.response.CommentResponse;
import com.example.springtodosubject.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{todoId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long todoId) {
        List<CommentResponse> commentList = commentService.getAllComments(todoId);
        return ResponseEntity.ok(commentList);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        CommentResponse comment = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request,
            HttpServletRequest req
    ) {
        CommentResponse comment = commentService.updateComment(commentId, request, req);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpServletRequest req) {
        commentService.deleteComment(commentId, req);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
