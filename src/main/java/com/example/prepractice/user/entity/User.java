package com.example.prepractice.user.entity;

import com.example.prepractice.constant.LoginType;
import com.example.prepractice.constant.UserStatus;
import com.example.prepractice.question.entity.Question;
import com.example.prepractice.question.entity.QuestionComment;
import com.example.prepractice.question.entity.QuestionVote;
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
@ToString
@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
}, name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    @Setter
    private String displayName;

    @Column(nullable = false)
    @Setter
    private boolean emailNotice;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    private UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    private LoginType loginType;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;


    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "user")
    List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<QuestionVote> questionVotes=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<QuestionComment> questionComments = new ArrayList<>();

    public void addQuestion(Question question){
        questions.add(question);
    }
    public void addQuestionVote(QuestionVote questionVote){
        questionVotes.add(questionVote);
    }
    public void addQuestionComment(QuestionComment questionComment){
        questionComments.add(questionComment);
    }


}
