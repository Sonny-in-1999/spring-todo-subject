package com.example.springtodosubject.todo.repository;

import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;


    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) ->
            new Todo(rs.getLong("todo_id"), rs.getString("name"), rs.getString("writer"), rs.getString("password"), rs.getDate("created_at"), rs.getDate("updated_at"));


    // 전건 조회
    public List<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper);
    }

    // 단건 조회
    public Todo findById(Long todoId) {
        return jdbcTemplate.queryForObject("SELECT * FROM todo WHERE todo_id = ?", todoRowMapper, todoId);
    }

    // 등록
    public int save(Todo todo) {
        return jdbcTemplate.update("INSERT INTO todo (todo_id, title, writer, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)",
                todo.getTodoId(), todo.getTitle(), todo.getWriter(), todo.getPassword(), todo.getCreatedAt(), todo.getUpdatedAt());
    }

    // 수정
    public int update(Long todoId, UpdateTodoRequest request, Date now) {
        return jdbcTemplate.update("UPDATE todo SET title = ?, writer = ?, updated_at = ? WHERE todo_id = ?",
                request.title(), request.writer(), now, todoId);
    }

    // 삭제
    public int delete(Long todoId) {
        return jdbcTemplate.update("DELETE FROM todo WHERE todo_id = ?", todoId);
    }
}
