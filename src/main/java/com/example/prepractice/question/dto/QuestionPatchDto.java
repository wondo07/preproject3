package com.example.prepractice.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionPatchDto {

    private String title;

    private String body;

    private String questionStatus;

    private Long userId;

}
