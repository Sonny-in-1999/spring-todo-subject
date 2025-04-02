package com.example.springtodosubject.todo.entity;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.dto.request.UpdateTodoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    // author_id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    // password
    @Column(name = "password", nullable = false)
    private String password;


    public void update(UpdateTodoRequest request) {
        this.title = request.title();
        this.startDate = LocalDate.parse(request.startDate());
        this.endDate = LocalDate.parse(request.endDate());
    }
}
