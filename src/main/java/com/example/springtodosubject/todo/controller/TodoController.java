package com.example.springtodosubject.todo.controller;

import com.example.springtodosubject.todo.dto.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.TodoResponse;
import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    // 전건 조회
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<TodoResponse>> getTodos(@PathVariable Long authorId) {
        List<TodoResponse> todos = todoService.getAllTodos(authorId);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long todoId) {
        TodoResponse todo = todoService.getTodoById(todoId);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody CreateTodoRequest request) {
        todoService.createTodo(request);
        return ResponseEntity.status(201).body("할 일 추가완료");
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long todoId, @RequestBody UpdateTodoRequest request) {
        TodoResponse todo = todoService.updateTodo(todoId, request);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        todoService.DeleteTodo(todoId);
        return ResponseEntity.ok().body("할 일 삭제완료");
    }
}
