package uz.com.RESTAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private Integer userId;
    @NotBlank(message = "Ismingiz kiriting iltimos aka!")
    private String firstName;
    @NotBlank(message = "Surname cannot be null or empty!")
    private String surName;
    @NotBlank(message = "Password cannot be null or empty!")
    private String password;
    @Email(message = "Emile cannot be null or empty!")
    private String emile;
    @NotNull(message = "Age cannot be null or empty!")
    private Integer age;
    private List<CardDto> cards;
}
