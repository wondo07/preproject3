package com.example.prepractice.question.dto;

import jakarta.persistence.GeneratedValue;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class QuestionPostDto {

    private String title;

    private String body;

    private Long userId;

    private String tag;
}
