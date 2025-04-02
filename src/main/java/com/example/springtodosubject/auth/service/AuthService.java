package com.example.springtodosubject.auth.service;

import com.example.springtodosubject.auth.component.PasswordEncoder;
import com.example.springtodosubject.auth.dto.LoginRequest;
import com.example.springtodosubject.author.dto.request.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.response.AuthorResponse;
import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public AuthorResponse register(CreateAuthorRequest request) {
        Author author = request.convertToEntity();
        author.setPassword(passwordEncoder.encode(request.password()));
        Author savedAuthor = authorRepository.save(author);
        return AuthorResponse.of(savedAuthor);
    }

    // 회원 인증
    public boolean authenticate(LoginRequest request) {
        return authorRepository.findByEmail(request.email())
                .map(author -> passwordEncoder.matches(request.password(), author.getPassword()))
                .orElse(false);
    }
}
