package com.codestates.hobby.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
    @AllArgsConstructor
    public static class Post {
        private String email;
        private String nickname;
        private String password;
        private String introduction;
        private String imgUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @JsonIgnore
        private Long memberId;
        private String nickname;
        private String password;
        private String introduction;
        private String imgUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String email;
        private String nickname;
        private String introduction;
        private String imgUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long id;
        private String nickname;
        private String imgUrl;
    }
}
