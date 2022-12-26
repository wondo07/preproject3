package com.example.prepractice.user.dto;


import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPatchDto {


    private String password;

    private String displayName;
}
