package com.example.springtodosubject.comment.entity;

import com.example.springtodosubject.author.entity.Author;
import com.example.springtodosubject.common.entity.BaseEntity;
import com.example.springtodosubject.todo.entity.Todo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "comment")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    // coment_id(pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    // comment
    @Column(name = "content")
    @NotNull
    private String content;

    // author_id(작성자 다대일 매핑)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    // todo_id(일정 다대일 매핑)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public void update(String content) {
        this.content = content;
    }
}
