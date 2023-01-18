package com.codestates.hobby.domain.auth.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
