package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
        private Long seriesId;
        private List<String> imgUrls;
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
        private long Id;
        private String title;
        private String content;
        private int views;
        private String category;
        private Long seriesId;
        private boolean isItWriter;
        private List<String> imgUrls;
        private List<PostCommentDto.Response> comments;
        private MemberDto.SimpleResponse writer;
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
        private Long seriesId;
        private boolean isItWriter;
        private String thumbnailUrl;
        private MemberDto.SimpleResponse writer;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}