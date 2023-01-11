package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @JsonIgnore
        private Long memberId;
        private String title;
        private String content;
        private String category;
        private long seriesId;
        private boolean isTemp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long memberId;
        private Long postId;
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
        private boolean isItWriter;
        private boolean isItTemp;
        private List<PostCommentDto.Response> comments;
        private MemberDto.SimpleResponse memberInfo;
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
        private int views;
        private int comments;
        private long seriesId;
        private String representImg;
        private MemberDto.SimpleResponse memberInfo;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}