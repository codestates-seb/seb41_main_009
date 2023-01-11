package com.codestates.hobby.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties("profileImage")
    public static class Post {
        private String email;
        private String nickname;
        private String password;
        private String introduction;
        private MultipartFile profileImage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties("profileImage")
    public static class Patch {
        private String nickname;
        private String password;
        private String introduction;
        private MultipartFile profileImage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String email;
        private String nickname;
        private String introduction;
        private String profileImageUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long id;
        private String nickname;
        private String profileImageUrl;
    }
}
