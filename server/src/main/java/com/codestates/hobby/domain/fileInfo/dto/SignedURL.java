package com.codestates.hobby.domain.fileInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignedURL {
	private String signedURL;
	private String fileURL;
	private ImageType type;
}
