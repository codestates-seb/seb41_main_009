package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long memberId;
        private String title;
        private String content;
        private String category;
        private long seriesId;
        private boolean isTemp;
        private MultipartFile thumbnail;

        public void setProperties(Long memberId, MultipartFile thumbnail){
            this.memberId = memberId;
            this.thumbnail = thumbnail;
        }
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
        private MultipartFile thumbnail;

        public void setProperties(Long memberId, Long postId, MultipartFile thumbnail){
            this.memberId = memberId;
            this.postId = postId;
            this.thumbnail = thumbnail;
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
        private long seriesId;
        private boolean isItWriter;
        private boolean isItTemp;
        private String thumbnailUrl;
        private List<PostCommentDto.Response> comments;
        private MemberDto.SimpleResponse writer;
        private List<SimpleResponse> categoryPosts;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }

    @Getter
    @Setter
    @Builder
    public static class SimpleResponse {
        private long Id;
        private String title;
        private String content;
        private String category;
        private int views;
        private int comments;
        private long seriesId;
        private boolean isItWriter;
        private String thumbnailUrl;
        private MemberDto.SimpleResponse writer;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
    }
}