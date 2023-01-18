package com.codestates.hobby.domain.fileInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponseDto {
	private Integer index;
	private String fileURL;
	private String signedURL;
	private ImageType contentType;

	public static FileResponseDto of(FileRequestDto req, String fileURL, String signedURL) {
		return new FileResponseDto(req.getIndex(), fileURL, signedURL, req.getContentType());
	}
}
