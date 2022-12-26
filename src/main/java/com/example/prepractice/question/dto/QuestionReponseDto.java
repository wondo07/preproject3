package com.example.prepractice.question.dto;


import com.example.prepractice.constant.QuestionStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionReponseDto {


        private Long QuestionId;

        private String title;

        private String body;

        private QuestionStatus questionStatus;

        private List<QuestionVoteResponseDto> questionVotes;

        private int viewCounting;

        private LocalDateTime createAt;

        private LocalDateTime updateAt;

}
