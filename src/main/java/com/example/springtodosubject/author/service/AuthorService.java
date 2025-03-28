package com.example.springtodosubject.author.service;

import com.example.springtodosubject.author.dto.response.AuthorResponse;
import com.example.springtodosubject.author.dto.request.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // 작성자 추가
    @Transactional
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        Author author = request.convertToEntity();
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor.convertToDTO();
    }

    // 작성자 단건 조회
    @Transactional(readOnly = true)
    public AuthorResponse getAuthorById(Long authorId) {
        Author author = validateAuthor(authorId);
        return author.convertToDTO();
    }

    // 작성자 수정
    @Transactional
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        Author author = validateAuthor(authorId);
        author.update(request);
        return author.convertToDTO();
    }

    // 작성자 삭제
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author author = validateAuthor(authorId);
        authorRepository.delete(author);
    }

    private Author validateAuthor(Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new IllegalArgumentException("존재하지 않는 작성자입니다.");
        }
    }
}
