package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.dto.FileResponseDto;
import com.codestates.hobby.domain.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ShowcaseDto {
	@Getter
	@Setter
	public static abstract class Request {
		@JsonIgnore
		private Long memberId;

		@NotBlank(message = "Content must not be empty.")
		private String content;

		@NotBlank(message = "Category must not be empty.")
		private String category;

		@Valid
		@Size(min = 1, message = "At least one file is required.")
		private List<FileRequestDto> fileInfos;
	}

	public static class Post extends Request {
	}

	@Getter @Setter
	public static class Patch extends Request {
		@JsonIgnore
		private Long showcaseId;
	}

	@Getter
	@Setter
	public static class Response {
		private long id;
		private String content;
		private String category;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private List<FileResponseDto> imageUrls;
		private List<ShowcaseCommentDto.Response> comments;
		private MemberDto.SimpleResponse writer;
	}

	@Getter
	@Setter
	public static class SimpleResponse {
		private long id;
		private String content;
		private String category;
		private int comments;
		private String thumbnailUrl;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
		private MemberDto.SimpleResponse writer;
		private ShowcaseCommentDto.Response lastComment;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public static class CommandResponse {
		private long id;
		private List<FileResponseDto> fileInfos;
	}
}
