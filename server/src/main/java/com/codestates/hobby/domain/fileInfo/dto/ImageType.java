package com.codestates.hobby.domain.fileInfo.dto;

import java.util.Arrays;

import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageType {
	PNG, JPEG, JPG, WEBP, GIF;

	private final String lowerCase;

	ImageType() {
		this.lowerCase = name().toLowerCase();
	}

	public String getExtension() {
		return lowerCase;
	}

	@JsonValue
	public String getFullName() {
		return "image/" + lowerCase;
	}

	@JsonCreator
	public static ImageType search(String type) {
		return Arrays.stream(ImageType.values())
			.filter(imageType -> type.toLowerCase().contains(imageType.lowerCase))
			.findAny()
			.orElseThrow(() -> new UnsupportedMediaTypeStatusException("Unsupported media type for " + type));
	}
}
