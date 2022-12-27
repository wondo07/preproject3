package com.example.prepractice.question.entity;


import com.example.prepractice.constant.QuestionStatus;
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
import java.util.ArrayList;
import java.util.List;

@Getter

@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "QUESTIONS")
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    @Setter
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @Setter
    private String body;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column
    private QuestionStatus questionStatus=QuestionStatus.Opend;

    @Setter
    @Column(nullable = false)
    private int viewCounting;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;


    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    List<QuestionComment> questionComments=new ArrayList<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    List<QuestionVote> questionVotes = new ArrayList<>();

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    private User user;


    public void addQueestionComment(QuestionComment questionComment){
        questionComments.add(questionComment);
    }

    public void addQuestionVote(QuestionVote questionVote){
        questionVotes.add(questionVote);
    }

    public void addUser(User user){
        this.user = user;
        user.addQuestion(this);
    }



}
