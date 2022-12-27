package com.example.prepractice.question.entity;


import com.example.prepractice.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity(name="QUESTION_COMMENT")
@NoArgsConstructor
public class QuestionComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long QuestionCommentId;

    @Column(nullable = false)
    @Setter
    private String Comment;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;


    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;


    @ManyToOne(optional = true,fetch=FetchType.LAZY)
    @Setter
    private Question question;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @Setter
    private User user;


    public void addQuestion(Question question){
        this.question =question;
        question.addQueestionComment(this);

    }

    public void addUser(User user){
        this.user = user;
        user.addQuestionComment(this);
    }

}
