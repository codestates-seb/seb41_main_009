package com.codestates.hobby.domain.fileInfo.service;

import java.util.Map;
import java.util.UUID;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;

public interface FileInfoService {
	Map<String, String> generateSignedURL(ImageType type, String basePath);

	default String generateRandomFilename(ImageType type, String basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
