package com.example.prepractice.user.dto;


import com.example.prepractice.constant.LoginType;
import com.example.prepractice.constant.UserStatus;
import com.example.prepractice.question.dto.QuestionReponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserResponseDto {


    private Long userId;

    private String email;

    private String password;


    private String displayName;

    private boolean emailNotice;

    private UserStatus userStatus;

    private LoginType loginType;

    private LocalDateTime createAt;


    private LocalDateTime updateAt;

    private List<QuestionReponseDto> questions;



}
