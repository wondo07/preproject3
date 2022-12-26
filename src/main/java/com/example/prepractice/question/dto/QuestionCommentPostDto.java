package com.example.prepractice.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionCommentPostDto {

    private Long userId;

    private String comment;


}
