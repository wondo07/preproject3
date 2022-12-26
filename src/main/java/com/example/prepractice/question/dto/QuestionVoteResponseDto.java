package com.example.prepractice.question.dto;

import com.example.prepractice.constant.QuestionVoteStatus;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionVoteResponseDto {


    private Long QuestionVoteId;

    private String questionVoteStatus;


    private LocalDateTime createAt;


    private LocalDateTime updateAt;

}
