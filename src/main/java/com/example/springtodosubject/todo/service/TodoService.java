package com.example.springtodosubject.todo.service;

import com.example.springtodosubject.todo.dto.CreateTodoRequest;
import com.example.springtodosubject.todo.dto.DeleteTodoRequest;
import com.example.springtodosubject.todo.dto.TodoResponse;
import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.entity.Todo;
import com.example.springtodosubject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;


    // 일정 전건 조회
    public List<TodoResponse> getAllTodos(Long authorId, int page, int size) {
        return todoRepository.findAllByAuthorId(authorId, page, size).stream()
                .map(Todo::convertToDTO)
                .toList();
    }

    // 일정 단건 조회
    public TodoResponse getTodoById(Long todoId) {
        Todo todo = todoRepository.findById(todoId);
        return todo.convertToDTO();
    }

    // 일정 등록
    public void createTodo(CreateTodoRequest request) {
        Todo todo = request.convertToEntity();
        todoRepository.save(todo);
    }

    // 일정 수정
    public TodoResponse updateTodo(Long todoId, UpdateTodoRequest request) {
        validateTodoWithPassword(todoId, request.password());
        todoRepository.update(todoId, request);
        Todo updatedTodo = todoRepository.findById(todoId);
        return updatedTodo.convertToDTO();
    }

    // 일정 삭제
    public void deleteTodo(Long todoId, DeleteTodoRequest request) {
        validateTodoWithPassword(todoId, request.password());
        todoRepository.delete(todoId);
    }

    // 비밀번호 검증
    private void validateTodoWithPassword(Long todoId, String password) {
        Todo todo = todoRepository.findById(todoId);
        if (!todo.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
