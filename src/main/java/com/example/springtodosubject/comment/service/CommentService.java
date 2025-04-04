package com.example.springtodosubject.comment.service;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import com.example.springtodosubject.comment.dto.request.CreateCommentRequest;
import com.example.springtodosubject.comment.dto.request.UpdateCommentRequest;
import com.example.springtodosubject.comment.dto.response.CommentResponse;
import com.example.springtodosubject.comment.entity.Comment;
import com.example.springtodosubject.comment.repository.CommentRepository;
import com.example.springtodosubject.common.exception.ForbiddenException;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthorRepository authorRepository;
    private final TodoRepository todoRepository;

    // 일정 댓글 목록 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> getAllComments(Long todoId) {
        List<Comment> commentList = commentRepository.getByTodo_TodoId(todoId);
        return commentList.stream().map(CommentResponse::of).toList();
    }

    // 일정 댓글 등록
    @Transactional
    public CommentResponse createComment(Long todoId, CreateCommentRequest request, HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");

        Author author = authorRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 작성자입니다."));

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));

        Comment comment = request.convertToEntity(todo, author);
        Comment savedComment = commentRepository.save(comment);

        return CommentResponse.of(savedComment);
    }

    // 일정 댓글 수정
    @Transactional
    public CommentResponse updateComment(Long commentId, UpdateCommentRequest request, HttpServletRequest req) {
        Comment comment = validateComment(commentId);

        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");

        // 세션 정보와 일치하지 않는 경우 권한 없음
        if (!comment.getAuthor().getEmail().equals(userEmail)) {
            throw new ForbiddenException("댓글 삭제 권한이 없습니다.");
        }
        comment.update(request.content());
        return CommentResponse.of(comment);
    }

    // 일정 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, HttpServletRequest req) {
        Comment comment = validateComment(commentId);

        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");

        // 세션 정보와 일치하지 않는 경우 권한 없음
        if (!comment.getAuthor().getEmail().equals(userEmail)) {
            throw new ForbiddenException("댓글 삭제 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }

    private Comment validateComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다."));
    }
}


