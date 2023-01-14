package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

public abstract class FileInfoService {
	protected final FileInfoRepository fileInfoRepository;

	FileInfoService(FileInfoRepository fileInfoRepository) {
		this.fileInfoRepository = fileInfoRepository;
	}

	abstract public SignedURL generateSignedURL(ImageType type, BasePath basePath);

	public List<SignedURL> generateSignedURLs(List<ImageType> types, BasePath basePath) {
		return types.parallelStream()
			.map(type -> generateSignedURL(type, basePath))
			.collect(Collectors.toList());
	}

	abstract public void delete(List<FileInfo> fileInfos);

	public void delete(FileInfo fileInfo) {
		fileInfoRepository.delete(fileInfo);
	}

	protected String generateRandomFilename(ImageType type, String basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
