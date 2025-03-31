package com.example.springtodosubject.author.controller;

import com.example.springtodosubject.author.dto.request.DeleteAuthorRequest;
import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.author.dto.response.AuthorResponse;
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
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId, @RequestBody DeleteAuthorRequest request) {
        authorService.deleteAuthor(authorId, request);
        return ResponseEntity.ok("작성자 삭제완료");
    }
}
