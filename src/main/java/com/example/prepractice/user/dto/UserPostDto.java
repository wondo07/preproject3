package com.example.prepractice.user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;




@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String displayName;

}
