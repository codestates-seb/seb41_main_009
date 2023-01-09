package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShowcaseDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Post {
		private String content;
		private String category;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Patch {
		private String content;
		private String category;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String content;
		private String category;
		private int views;
		private boolean isItWriter;
		private List<String> imageUrls;
		private ShowcaseCommentDto.Response comments;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;

		// TODO: Writer(nickname, profileImageUrl)
		//     : 이미지의 순서 정보가 있어야됨
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SimpleResponse {
		private long id;
		private String content;
		private String category;
		private int views;
		private int comments;
		private boolean isItWriter;
		private String thumbnailUrl;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;

		// TODO: Writer(nickname, profileImageUrl)
	}
}
