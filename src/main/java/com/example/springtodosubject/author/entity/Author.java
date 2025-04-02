package com.example.springtodosubject.author.entity;

import com.example.springtodosubject.author.dto.request.UpdateAuthorRequest;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.entity.Todo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "author")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author extends BaseEntity {

    // author_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    // name
    @Column(name = "name", nullable = false)
    private String name;

    // email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // password
    @Column(name = "password", nullable = false)
    @Setter
    private String password;

    // 일정 일대다 매핑
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todoList = new ArrayList<>();

    // 작성자 정보 수정
    public void update(UpdateAuthorRequest request) {
        if (!request.password().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }
}
