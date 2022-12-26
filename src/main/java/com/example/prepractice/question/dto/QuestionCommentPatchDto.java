package com.example.prepractice.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionCommentPatchDto {

    private Long userId;

    private String Comment;


}
