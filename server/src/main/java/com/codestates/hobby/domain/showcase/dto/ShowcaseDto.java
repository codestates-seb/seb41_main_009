package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShowcaseDto {
	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties({"memberId", "imgFiles"})
	public static class Post {
		private Long memberId;
		private String content;
		private String category;
		private List<MultipartFile> imgFiles;

		public void setProperties(Long memberId, List<MultipartFile> imgFiles) {
			this.memberId = memberId;
			this.imgFiles = imgFiles;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@JsonIgnoreProperties({"memberId", "showcaseId", "imgFiles"})
	public static class Patch {
		private Long memberId;
		private Long showcaseId;
		private String content;
		private String category;
		private List<MultipartFile> imgFiles;

		public void setProperties(Long memberId, Long showcaseId, List<MultipartFile> imgFiles) {
			this.showcaseId = showcaseId;
			this.memberId = memberId;
			this.imgFiles = imgFiles;
		}
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