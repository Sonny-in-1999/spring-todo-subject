package com.example.springtodosubject.todo.service;

import com.example.springtodosubject.todo.dto.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.TodoResponse;
import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 전건 조회
    public List<TodoResponse> getAllTodos(Long authorId, int page, int size) {
        return todoRepository.findAllByAuthorId(authorId, page, size).stream()
                .map(Todo::convertToDTO)
                .toList();
    }

    // 단건 조회
    public TodoResponse getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId);
        return todo.convertToDTO();
    }

    // 등록
    public void createTodo(CreateTodoRequest request) {
        Todo todo = request.convertToEntity();
        todoRepository.save(todo);
    }

    // 수정
    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(todoId);
        if (!todo.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        todoRepository.update(todoId, request, now);
        Todo updatedTodo = todoRepository.findById(todoId);
        return updatedTodo.convertToDTO();
    }

    // 삭제
    public void DeleteTodo(Long todoId) {
        todoRepository.delete(todoId);
    }
}
