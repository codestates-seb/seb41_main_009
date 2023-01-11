package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;
import java.util.UUID;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

public interface FileInfoService {
	SignedURL generateSignedURL(ImageType type, String basePath);

	void delete(FileInfo fileInfo);

	void delete(List<FileInfo> fileInfos);

	default String generateRandomFilename(ImageType type, String basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
