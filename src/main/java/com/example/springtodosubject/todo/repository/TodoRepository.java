package com.example.springtodosubject.todo.repository;

import com.example.springtodosubject.todo.dto.UpdateTodoRequest;
import com.example.springtodosubject.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;


    private final RowMapper<Todo> todoRowMapper = (rs, rowNum) ->
            new Todo(rs.getLong("todo_id"), rs.getString("title"), rs.getLong("author_id"), rs.getString("password"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));


    // 일정 전건 조회
    public List<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todo", todoRowMapper);
    }

    // 작성자 고유 식별자로 일정 전건 조회
    public List<Todo> findAllByAuthorId(Long authorId, int page, int size) {
        String sql = """
            SELECT t.todo_id, t.title, t.password, t.created_at, t.updated_at,
                   a.author_id, a.name, a.email, a.created_at AS author_created_at, a.updated_at AS author_updated_at
            FROM todo t
            JOIN author a ON t.author_id = a.author_id
            WHERE a.author_id = ?
            LIMIT ? OFFSET ?
        """;

        return jdbcTemplate.query(sql, new Object[]{authorId, size, page * size}, (rs, rowNum) ->
                Todo.builder()
                        .todoId(rs.getLong("todo_id"))
                        .title(rs.getString("title"))
                        .password(rs.getString("password"))
                        .createdAt(rs.getTimestamp("created_at"))
                        .updatedAt(rs.getTimestamp("updated_at"))
                        .build()
        );
    }

    // 일정 단건 조회
    public Todo findById(Long todoId) {
        return jdbcTemplate.queryForObject("SELECT * FROM todo WHERE todo_id = ?", todoRowMapper, todoId);
    }

    // 일정 등록
    public int save(Todo todo) {
        return jdbcTemplate.update("INSERT INTO todo (title, author_id, password) VALUES (?, ?, ?)",
                todo.getTitle(), todo.getAuthorId(), todo.getPassword());
    }

    // 일정 수정
    public int update(Long todoId, UpdateTodoRequest request) {
        return jdbcTemplate.update("UPDATE todo SET title = ?, updated_at = NOW() WHERE todo_id = ? AND author_id = ?",
                request.title(), todoId, request.authorId());
    }

    // 일정 삭제
    public int delete(Long todoId) {
        return jdbcTemplate.update("DELETE FROM todo WHERE todo_id = ?", todoId);
    }
}
