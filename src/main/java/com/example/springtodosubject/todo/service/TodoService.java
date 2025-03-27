package com.example.springtodosubject.todo.service;

import com.example.springtodosubject.common.dto.PageResponse;
import com.example.springtodosubject.todo.dto.*;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 일정 전건 조회
    @Transactional(readOnly = true)
    public PageResponse<List<TodoResponse>> getAllTodos(Long authorId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_at");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Todo> todoPage = todoRepository.findAllByAuthorId(authorId, pageable);
        List<TodoResponse> responseList = todoPage.stream().map(Todo::convertToDTO).toList();

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
        return todo.convertToDTO();
    }

    // 일정 등록
    @Transactional
    public TodoResponse createTodo(CreateTodoRequest request) {
        Todo todo = request.convertToEntity();
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo.convertToDTO();
    }

    // 일정 수정
    @Transactional
    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest request) {
        Todo todo = validateTodoWithPassword(todoId, request.password());
        todo.update(request.title()); // 더티체킹 -> save() 불필요
        return todo.convertToDTO();
    }

    // 일정 삭제
    @Transactional
    public void deleteTodo(Long todoId, DeleteTodoRequest request) {
        Todo todo = validateTodoWithPassword(todoId, request.password());
        todoRepository.delete(todo);
    }

    // 존재하는 일정인지 검증
    private Todo validateTodo(Long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
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
