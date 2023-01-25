package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShowcaseCommentDto {
	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties({"showcaseId", "memberId"})
	public static class Post {
		private Long showcaseId;
		private Long memberId;

		@NotBlank(message = "Content must not be empty.")
		private String content;

		public void setProperties(Long showcaseId, Long memberId) {
			this.showcaseId = showcaseId;
			this.memberId = memberId;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties({"memberId", "showcaseId", "commentId"})
	public static class Patch {
		private Long showcaseId;
		private Long commentId;
		private Long memberId;

		@NotBlank(message = "Content must not be empty.")
		private String content;

		public void setProperties(Long memberId, Long showcaseId, Long commentId) {
			this.showcaseId = showcaseId;
			this.commentId = commentId;
			this.memberId = memberId;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String content;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private MemberDto.SimpleResponse writer;
	}
}
