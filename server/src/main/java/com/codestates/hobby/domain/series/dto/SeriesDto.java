package com.codestates.hobby.domain.series.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.post.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class SeriesDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String title;
        private String content;
        private String category;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private String title;
        private String content;
        private String category;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private String content;
        private String category;
        private int views;
        private String thumbnailUrl;
        private MemberDto.SimpleResponse member;
        private PostDto.Response post;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long id;
        private String title;
        private String content;
        private int views;
        private MemberDto.SimpleResponse member;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}