package com.example.springtodosubject.todo.entity;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "todo")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    // todo_id(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    // title
    @Column(name = "title", nullable = false)
    private String title;

    // author_id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    // password
    @Column(name = "password", nullable = false)
    private String password;


    public void update(String title) {
        this.title = title;
    }
}
