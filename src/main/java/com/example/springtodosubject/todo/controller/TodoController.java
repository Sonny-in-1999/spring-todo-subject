package com.example.springtodosubject.todo.controller;

import com.example.springtodosubject.common.dto.PageResponse;
import com.example.springtodosubject.todo.dto.request.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.request.DeleteTodoRequest;
import com.example.springtodosubject.todo.dto.request.UpdateTodoRequest;
import com.example.springtodosubject.todo.dto.response.TodoResponse;
import com.example.springtodosubject.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    // 전건 조회
    @GetMapping
    public ResponseEntity<PageResponse<List<TodoResponse>>> getTodos(
            @RequestParam(required = false) Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<List<TodoResponse>> todoList = todoService.getAllTodos(authorId, page, size);
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long todoId) {
        TodoResponse todo = todoService.getTodoById(todoId);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody CreateTodoRequest request) {
        TodoResponse todo = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long todoId,
            @RequestBody UpdateTodoRequest request,
            HttpServletRequest req
    ) {
        TodoResponse todo = todoService.updateTodo(todoId, request, req);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(
            @PathVariable Long todoId,
            @RequestBody DeleteTodoRequest request,
            HttpServletRequest req
    ) {
        todoService.deleteTodo(todoId, request, req);
        return ResponseEntity.ok("할 일 삭제완료");
    }
}
