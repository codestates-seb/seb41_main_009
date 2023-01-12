package com.codestates.hobby.domain.fileInfo.dto;

import java.util.Arrays;

import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageType {
	PNG, JPEG, WEBP, GIF;

	private final String lowerCase;

	ImageType() {
		this.lowerCase = name().toLowerCase();
	}

	public String getExtension() {
		return lowerCase;
	}

	@JsonValue
	public String toContentType() {
		return "image/" + lowerCase;
	}

	@JsonCreator
	public static ImageType search(String type) {
		return Arrays.stream(ImageType.values())
			.filter(imageType -> type.equalsIgnoreCase(imageType.name()))
			.findAny()
			.orElseThrow(() -> new UnsupportedMediaTypeStatusException("Unsupported media type for " + type));
	}
}
