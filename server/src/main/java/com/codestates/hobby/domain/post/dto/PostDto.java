package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @JsonIgnore
        private Long memberId;
        @NotBlank(message = "Title must not be empty.")
        private String title;
        @NotBlank(message = "Content must not be empty.")
        private String content;
        private String description;
        @NotBlank(message = "Category must not be empty.")
        private String category;
        private Long seriesId;
        private List<String> imgUrls;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        @JsonIgnore
        private Long memberId;
        @JsonIgnore
        private Long postId;
        @NotBlank(message = "Title must not be empty.")
        private String title;
        @NotBlank(message = "Content must not be empty.")
        private String content;
        private String description;
        @NotBlank(message = "Category must not be empty.")
        private String category;
        private Long seriesId;
        private List<String> imgUrls;

        public void setProperties(Long memberId, Long postId){
            this.memberId = memberId;
            this.postId = postId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private String content;
        private String description;
        private int views;
        private String category;
        private Long seriesId;
        private MemberDto.SimpleResponse writer;
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
        private String description;
        private String category;
        private int views;
        private int comments;
        private Long seriesId;
        private String thumbnailUrl;
        private MemberDto.SimpleResponse writer;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}