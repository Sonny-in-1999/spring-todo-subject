package com.example.springtodosubject.author.service;

import com.example.springtodosubject.author.dto.AuthorResponse;
import com.example.springtodosubject.author.dto.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.UpdateAuthorRequest;
import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // 작성자 추가
    public void createAuthor(CreateAuthorRequest request) {
        Author author = request.convertToEntity();
        authorRepository.save(author);
    }

    // 작성자 단건 조회
    public AuthorResponse getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId);
        return author.convertToDTO();
    }

    // 작성자 수정
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        authorRepository.update(authorId, request);
        Author author = authorRepository.findById(authorId);
        return author.convertToDTO();
    }

    // 작성자 삭제
    public void deleteAuthor(Long authorId) {
        authorRepository.delete(authorId);
    }
}
