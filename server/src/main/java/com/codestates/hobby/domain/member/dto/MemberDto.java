package com.codestates.hobby.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank(message = "Email must not be empty.")
        @Email
        private String email;

        @NotBlank(message = "Nickname must not be empty.")
        @Pattern(regexp = "[A-Za-z0-9가-힣]{2,15}", message = "영어 대소문자, 한글, 숫자만 가능, 2~15자로 제한")
        private String nickname;

        @NotBlank(message = "Password must not be empty.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",
                message = "최소 하나의 문자, 숫자, 특문를 포함해야하며 8자리 이상")
        private String password;

        @Size(max = 255, message = "255자 이하로 제한됩니다.")
        private String introduction;

        private String profileUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @JsonIgnore
        private Long memberId;

        @Pattern(regexp = "[A-Za-z0-9가-힣]{2,15}", message = "영어 대소문자, 한글, 숫자만 가능, 2~15자로 제한")
        private String nickname;

        @Size(max = 255, message = "255자 이하로 제한됩니다.")
        private String introduction;

        private String profileUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String email;
        private String nickname;
        private String introduction;
        private String profileUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long id;
        private String nickname;
        private String profileUrl;
    }
}
