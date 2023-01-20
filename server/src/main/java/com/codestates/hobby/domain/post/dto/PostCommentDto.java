package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PostCommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties({"postId", "memberId"})
    public static class Post {
        private Long postId;
        private Long memberId;
        @NotBlank(message = "Content must not be empty.")
        private String content;

        public void setProperties(Long postId, Long memberId) {
            this.postId = postId;
            this.memberId = memberId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties({"memberId", "postId", "commentId"})
    public static class Patch {
        private Long postId;
        private Long commentId;
        private Long memberId;
        @NotBlank(message = "Content must not be empty.")
        private String content;

        public void setProperties(Long memberId, Long postId, Long commentId) {
            this.postId = postId;
            this.commentId = commentId;
            this.memberId = memberId;
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{
        private long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime lastModifiedAt;
        private MemberDto.SimpleResponse writer;
    }
}
