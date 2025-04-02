package com.example.springtodosubject.todo.entity;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.comment.entity.Comment;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.dto.request.UpdateTodoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "todo_id")
    private Long todoId;

    // title
    @Column(name = "title", nullable = false)
    private String title;

    // start_date
    @Column(name = "start_date")
    private LocalDate startDate;

    // end_date
    @Column(name = "end_date")
    private LocalDate endDate;

    // author_id(작성자 다대일 매핑)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    // password
    @Column(name = "password", nullable = false)
    private String password;

    // 댓글 일대다 매핑
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    public void update(UpdateTodoRequest request) {
        this.title = request.title();
        this.startDate = LocalDate.parse(request.startDate());
        this.endDate = LocalDate.parse(request.endDate());
    }
}
