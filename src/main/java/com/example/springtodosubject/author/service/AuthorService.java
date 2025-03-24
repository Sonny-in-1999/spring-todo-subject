package com.example.springtodosubject.author.service;

import com.example.springtodosubject.author.dto.AuthorResponse;
import com.example.springtodosubject.author.dto.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.UpdateAuthorRequest;
import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // 작성자 추가
    public void createAuthor(CreateAuthorRequest request) {
        Author author = request.convertToEntity();
        authorRepository.save(author);
    }

    // 작성자 수정
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        Timestamp now = new Timestamp(System.currentTimeMillis()); // 현재시간
        authorRepository.update(authorId, request, now);
        Author author = authorRepository.findById(authorId);
        return author.convertToDTO();
    }

    // 작성자 삭제
    public void deleteAuthor(Long authorId) {
        authorRepository.delete(authorId);
    }
}
