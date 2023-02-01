package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

public class ShowcaseCommentDto {
	@Getter
	@JsonIgnoreProperties({"showcaseId", "memberId"})
	public static abstract class Request {
		@NotBlank(message = "Content must not be empty.")
		private String content;
		private Long showcaseId;
		private Long memberId;

		public void setProperties(Long showcaseId, Long memberId) {
			this.showcaseId = showcaseId;
			this.memberId = memberId;
		}
	}

	public static class Post extends Request {
	}

	@Getter
	public static class Patch  extends Request {
		@JsonIgnore
		private Long commentId;

		public void setProperties(Long memberId, Long showcaseId, Long commentId) {
			this.setProperties(showcaseId, memberId);
			this.commentId = commentId;
		}
	}

	@Getter
	@Setter
	public static class Response {
		private long id;
		private String content;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private MemberDto.SimpleResponse writer;
	}
}