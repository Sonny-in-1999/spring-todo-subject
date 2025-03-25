package com.example.springtodosubject.todo.controller;

import com.example.springtodosubject.todo.dto.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.DeleteTodoRequest;
import com.example.springtodosubject.todo.dto.TodoResponse;
import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.service.TodoService;
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
    public ResponseEntity<List<TodoResponse>> getTodos(
            @RequestParam Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<TodoResponse> todos = todoService.getAllTodos(authorId, page, size);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long todoId) {
        TodoResponse todo = todoService.getTodoById(todoId);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody CreateTodoRequest request) {
        todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("할 일 추가완료");
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long todoId, @RequestBody UpdateTodoRequest request) {
        TodoResponse todo = todoService.updateTodo(todoId, request);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId, @RequestBody DeleteTodoRequest request) {
        todoService.deleteTodo(todoId, request);
        return ResponseEntity.ok().body("할 일 삭제완료");
    }
}
