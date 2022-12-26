package com.example.prepractice.question.dto;


import com.example.prepractice.constant.QuestionVoteStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionVotePatchDto {


    private Long userId;


    private String questionVoteStatus;
}
