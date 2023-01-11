package com.codestates.hobby.domain.fileInfo.dto;

import java.util.Arrays;

public enum ImageType {
	PNG, JPEG, WEBP, GIF;

	public String toContentType() {
		return "image/" + name().toLowerCase();
	}

	public String getExtension() {
		return name().toLowerCase();
	}

	public static ImageType search(String type) {
		return Arrays.stream(ImageType.values())
			.filter(imageType ->  type.equalsIgnoreCase(imageType.name()))
			.findAny()
			.orElse(null);
	}
}
