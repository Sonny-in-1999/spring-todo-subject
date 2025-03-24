package com.example.springtodosubject.author.controller;

import com.example.springtodosubject.author.dto.AuthorResponse;
import com.example.springtodosubject.author.dto.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.UpdateAuthorRequest;
import com.example.springtodosubject.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;


    // 작성자 추가
    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody CreateAuthorRequest request) {
        authorService.createAuthor(request);
        return ResponseEntity.status(201).body("작성자 추가완료");
    }

    // 작성자 단건 조회
    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable Long authorId) {
        AuthorResponse author = authorService.getAuthorById(authorId);
        return ResponseEntity.ok(author);
    }
    
    // 작성자 수정
    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long authorId, @RequestBody UpdateAuthorRequest request) {
        AuthorResponse author = authorService.updateAuthor(authorId, request);
        return ResponseEntity.ok(author);
    }

    // 작성자 삭제
    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok("작성자 삭제완료");
    }
}
