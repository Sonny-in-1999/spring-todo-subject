package com.example.springtodosubject.todo.service;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import com.example.springtodosubject.common.dto.PageResponse;
import com.example.springtodosubject.common.exception.ForbiddenException;
import com.example.springtodosubject.todo.dto.request.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.request.DeleteTodoRequest;
import com.example.springtodosubject.todo.dto.request.UpdateTodoRequest;
import com.example.springtodosubject.todo.dto.response.TodoResponse;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthorRepository authorRepository;


    // 일정 전건 조회
    @Transactional(readOnly = true)
    public PageResponse<List<TodoResponse>> getAllTodos(Long authorId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_at");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Todo> todoPage;
        if (authorId != null) { // 작성자 pk 값을 받은경우 해당 작성자의 일정만 조회
            todoPage = todoRepository.findAllByAuthorId(authorId, pageable);
        } else { // 아니면 전부 조회
            todoPage = todoRepository.findAll(pageable);
        }

        List<TodoResponse> responseList = todoPage.stream().map(TodoResponse::of).toList();

        return PageResponse.<List<TodoResponse>>builder()
                .data(responseList)
                .currentPage(todoPage.getTotalPages())
                .totalPage(todoPage.getTotalPages())
                .totalSize(todoPage.getTotalElements())
                .build();
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public TodoResponse getTodoById(Long todoId) {
        Todo todo = validateTodo(todoId);
        return TodoResponse.of(todo);
    }

    // 일정 등록
    @Transactional
    public TodoResponse createTodo(CreateTodoRequest request, HttpServletRequest req) {
        // 세션 정보로 유저 조회
        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");
        Author author = authorRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 작성자입니다."));
        
        Todo todo = request.convertToEntity(author);
        Todo savedTodo = todoRepository.save(todo);
        return TodoResponse.of(savedTodo);
    }

    // 일정 수정
    @Transactional
    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest request, HttpServletRequest req) {
        Todo todo = validateTodoWithPassword(todoId, request.password());

        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");

        // 세션 정보와 일치하지 않는 경우 권한 없음
        if (!todo.getAuthor().getEmail().equals(userEmail)) {
            throw new ForbiddenException("댓글 삭제 권한이 없습니다.");
        }
        todo.update(request); // 더티체킹 -> save() 불필요
        return TodoResponse.of(todo);
    }

    // 일정 삭제
    @Transactional
    public void deleteTodo(Long todoId, DeleteTodoRequest request, HttpServletRequest req) {
        Todo todo = validateTodoWithPassword(todoId, request.password());

        HttpSession session = req.getSession(false);
        String userEmail = (String) session.getAttribute("user");

        // 세션 정보와 일치하지 않는 경우 권한 없음
        if (!todo.getAuthor().getEmail().equals(userEmail)) {
            throw new ForbiddenException("댓글 삭제 권한이 없습니다.");
        }
        todoRepository.delete(todo);
    }

    // 존재하는 일정인지 검증
    private Todo validateTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다."));
    }

    // 비밀번호 검증
    private Todo validateTodoWithPassword(Long todoId, String password) {
        Todo todo = validateTodo(todoId);
        if (todo.getPassword().equals(password)) {
            return todo;
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
