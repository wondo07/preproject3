package com.example.prepractice.question.dto;


import com.example.prepractice.constant.QuestionVoteStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionVotePostDto {

    private Long userId;

    private Long questionId;

    private String questionVoteStatus;
}
