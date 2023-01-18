package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

public abstract class FileInfoService {
	protected final FileInfoRepository fileInfoRepository;

	FileInfoService(FileInfoRepository fileInfoRepository) {
		this.fileInfoRepository = fileInfoRepository;
	}

	abstract public FileInfo generateSignedURL(FileRequestDto request, BasePath basePath);

	public List<FileInfo> generateSignedURLs(List<FileRequestDto> requests, BasePath basePath) {
		return requests.parallelStream()
			.map(req -> generateSignedURL(req, basePath))
			.collect(Collectors.toList());
	}

	public void delete(FileInfo fileInfo) {
		fileInfoRepository.delete(fileInfo);
	}

	public void delete(List<FileInfo> fileInfos) {
		fileInfoRepository.deleteAll(fileInfos);
	}

	protected String generateRandomFilename(ImageType type, BasePath basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
