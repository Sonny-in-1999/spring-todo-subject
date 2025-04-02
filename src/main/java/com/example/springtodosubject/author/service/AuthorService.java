package com.example.springtodosubject.author.service;

import com.example.springtodosubject.auth.component.PasswordEncoder;
import com.example.springtodosubject.author.dto.request.DeleteAuthorRequest;
import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.author.dto.response.AuthorResponse;
import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.author.repository.AuthorRepository;
import com.example.springtodosubject.common.exception.ForbiddenException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public AuthorResponse updateAuthor(Long authorId, UpdateAuthorRequest request, HttpServletRequest req) {
        Author author = validateAuthor(authorId);
        HttpSession session = req.getSession();
        String userEmail = (String) session.getAttribute("user");
        if (author.getEmail().equals(userEmail)) {
            throw new ForbiddenException("유저 정보 수정 권한이 없습니다.");
        }

        if (passwordEncoder.matches(request.password(), author.getPassword())) {
            author.update(request);
            return AuthorResponse.of(author);
        } else {
            throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해주세요.");
        }
    }

    // 작성자 삭제
    @Transactional
    public void deleteAuthor(Long authorId, DeleteAuthorRequest request, HttpServletRequest req) {
        Author author = validateAuthor(authorId);
        HttpSession session = req.getSession();
        String userEmail = (String) session.getAttribute("user");
        if (author.getEmail().equals(userEmail)) {
            throw new ForbiddenException("유저 삭제 권한이 없습니다.");
        }
        
        if (passwordEncoder.matches(request.password(), author.getPassword())) {
            authorRepository.delete(author);
        } else {
            throw new IllegalArgumentException("비밀번호를 다시 한 번 확인해주세요.");
        }
    }

    private Author validateAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 작성자입니다."));
    }
}
