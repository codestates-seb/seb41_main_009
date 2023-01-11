package com.codestates.hobby.domain.post.dto;

import com.codestates.hobby.domain.member.dto.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class PostCommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{
        private long id;
        private String content;
        private boolean isItWriter;
        private LocalDateTime createdAt;
        private LocalDateTime lastModifiedAt;
        private MemberDto.SimpleResponse writer;
    }
}
