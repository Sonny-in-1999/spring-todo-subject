package com.example.springtodosubject.todo.service;

import com.example.springtodosubject.todo.dto.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.TodoResponse;
import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 전건 조회
    public List<TodoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(Todo::convertToDTO)
                .toList();
    }

    // 단건 조회
    public TodoResponse getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId);
        return todo.convertToDTO();
    }

    // 등록
    public TodoResponse createTodo(CreateTodoRequest request) {
        Todo todo = request.convertToEntity();
        todoRepository.save(todo);
        return todo.convertToDTO();
    }

    // 수정
    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest request) {
        Date now = new Date(System.currentTimeMillis()); // 현재시간
        todoRepository.update(todoId, request, now);
        Todo todo = todoRepository.findById(todoId);
        return todo.convertToDTO();
    }

    // 삭제
    public void DeleteTodo(Long todoId) {
        todoRepository.delete(todoId);
    }
}
