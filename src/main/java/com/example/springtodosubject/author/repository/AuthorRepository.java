package com.example.springtodosubject.author.repository;

import com.example.springtodosubject.author.dto.UpdateAuthorRequest;
import com.example.springtodosubject.author.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Author> authorRowMapper = (rs, rowNum) ->
            new Author(rs.getLong("author_id"), rs.getString("name"), rs.getString("email"), rs.getTimestamp("created_at"), rs.getTimestamp("updated_at"));


    // 단건 조회
    public Author findById(Long authorId) {
        return jdbcTemplate.queryForObject("SELECT * FROM author WHERE author_id = ?", authorRowMapper, authorId);
    }

    // 등록
    public int save(Author author) {
        return jdbcTemplate.update("INSERT INTO author (name, email) VALUES (?, ?)",
                author.getName(), author.getEmail());
    }

    // 수정
    public int update(Long authorId, UpdateAuthorRequest request) {
        return jdbcTemplate.update("UPDATE author SET name = ?, email = ?, updated_at = NOW() WHERE author_id = ?",
                request.name(), request.email(), authorId);
    }

    // 삭제
    public int delete(Long authorId) {
        return jdbcTemplate.update("DELETE FROM author WHERE author_id = ?", authorId);
    }
}
