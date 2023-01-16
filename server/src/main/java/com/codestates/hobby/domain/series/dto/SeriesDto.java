package com.codestates.hobby.domain.series.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SeriesDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @JsonIgnore
        private long memberId;

        @NotBlank(message = "Title must not be empty.")
        private String title;

        @NotBlank(message = "Content must not be empty.")
        private String content;

        @NotBlank(message = "Category must not be empty.")
        private String category;
        private String thumbnail;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties({"memberId", "seriesId"})
    public static class Patch {
        private long memberId;
        private long seriesId;

        @NotBlank(message = "Title must not be empty.")
        private String title;

        @NotBlank(message = "Content must not be empty.")
        private String content;

        @NotBlank(message = "Category must not be empty.")
        private String category;
        private String thumbnail;

        public void serProperties(long memberId, long seriesId) {
            this.memberId = memberId;
            this.seriesId = seriesId;
        }
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
        private PostDto.SimpleResponse post;
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
        private String thumbnailUrl;
        private MemberDto.SimpleResponse member;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}