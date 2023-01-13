package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShowcaseDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Post {
		@JsonIgnore
		private Long memberId;
		private String content;
		private String category;
		private List<String> imageUrls;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties({"memberId", "showcaseId"})
	public static class Patch {
		private Long memberId;
		private Long showcaseId;
		private String content;
		private String category;
		private List<String> imageUrls;

		public void setProperties(Long memberId, Long showcaseId) {
			this.showcaseId = showcaseId;
			this.memberId = memberId;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String content;
		private String category;
		private boolean isItWriter;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private List<String> imageUrls;
		private List<ShowcaseCommentDto.Response> comments;
		private MemberDto.SimpleResponse writer;

		// TODO: 이미지의 순서 정보가 있어야됨
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SimpleResponse {
		private long id;
		private String content;
		private String category;
		private int comments;
		private boolean isItWriter;
		private String thumbnailUrl;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private MemberDto.SimpleResponse writer;
	}
}
