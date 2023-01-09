package com.codestates.hobby.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String email;
        private String nickname;
        private String password;
        private String introduction;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private String nickname;
        private String password;
        private String introduction;
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
}
