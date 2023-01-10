package com.codestates.hobby.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
//        private MemberDto.SimpleResponse memberInfo;
        private List<SimpleResponse> categoryPosts;
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
        private String representImg;
//        private MemberDto.SimpleResponse memberInfo;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}