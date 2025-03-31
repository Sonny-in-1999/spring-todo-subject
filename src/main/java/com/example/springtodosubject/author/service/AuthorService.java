package com.example.springtodosubject.author.service;

import com.example.springtodosubject.auth.component.PasswordEncoder;
import com.example.springtodosubject.author.dto.request.DeleteAuthorRequest;
import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.author.dto.response.AuthorResponse;
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
    private final PasswordEncoder passwordEncoder;

    // 작성자 단건 조회
    public AuthorResponse getAuthorById(Long authorId) {
        Author author = validateAuthor(authorId);
        return AuthorResponse.of(author);
    }

    // 작성자 수정
    @Transactional
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request) {
        Author author = validateAuthor(authorId);
        if (passwordEncoder.matches(request.password(), author.getPassword())) {
            author.update(request);
            return AuthorResponse.of(author);
        } else {
            throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해주세요.");
        }
    }

    // 작성자 삭제
    @Transactional
    public void deleteAuthor(Long authorId, DeleteAuthorRequest request) {
        Author author = validateAuthor(authorId);
        if (passwordEncoder.matches(request.password(), author.getPassword())) {
            authorRepository.delete(author);
        } else {
            throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해주세요.");
        }
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
