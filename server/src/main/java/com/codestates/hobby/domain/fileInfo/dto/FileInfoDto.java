package com.codestates.hobby.domain.fileInfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileInfoDto {
	@Getter
	@NoArgsConstructor
	public static class Post {
		private ImageType type;
	}
}
