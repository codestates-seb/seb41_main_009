package com.codestates.hobby.domain.showcase.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ShowcaseCommentDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String content;
		private boolean isItWriter;
		private LocalDateTime createdAt;
		private LocalDateTime lastModifiedAt;

		// TODO: Writer(nickname, profileImageUrl)
	}
}
