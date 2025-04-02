package com.example.springtodosubject.comment.entity;

import com.example.springtodosubject.common.entity.BaseEntity;
import jakarta.persistence.*;
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
}
