package com.codestates.hobby.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// isWriter??
//프론트 Post 목업 제공받기

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class post {
        private String title;
        private String content;
        private String category;
        private long seriesId;
        private boolean isTemp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class patch {
        private String title;
        private String content;
        private String category;
        private long seriesId;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long Id;
        private String title;
        private String content;
        private int views;
        private String category;
        private long seriesId;
        private boolean isTemp;
        private PostCommentDto.Response comments;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SimpleResponse {
        private long Id;
        private String title;
        private String content;
        private String category;
        private long seriesId;
        private boolean isTemp;
        private String representUrl;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}