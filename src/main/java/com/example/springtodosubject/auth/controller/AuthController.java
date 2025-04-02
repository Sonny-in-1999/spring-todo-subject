package com.example.springtodosubject.auth.controller;

import com.example.springtodosubject.auth.dto.LoginRequest;
import com.example.springtodosubject.auth.service.AuthService;
import com.example.springtodosubject.author.dto.request.CreateAuthorRequest;
import com.example.springtodosubject.author.dto.response.AuthorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthorResponse> register(@RequestBody CreateAuthorRequest request) {
        AuthorResponse author = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpServletRequest req) {
        if (authService.authenticate(request)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", request.email());
            return ResponseEntity.ok("로그인에 성공했습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효한 계정이 아닙니다.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("user") != null) {
            session.setAttribute("user", null); // 세션 초기화
        }
        return ResponseEntity.ok("로그아웃에 성공했습니다.");
    }
}
