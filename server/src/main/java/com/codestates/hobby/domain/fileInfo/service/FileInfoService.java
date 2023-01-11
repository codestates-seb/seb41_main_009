package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;
import java.util.UUID;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

public abstract class FileInfoService {
	protected final FileInfoRepository fileInfoRepository;

	FileInfoService(FileInfoRepository fileInfoRepository) {
		this.fileInfoRepository = fileInfoRepository;
	}

	abstract public SignedURL generateSignedURL(ImageType type, String basePath);

	public FileInfo save(FileInfo fileInfo) {
		return fileInfoRepository.save(fileInfo);
	}

	public List<FileInfo> save(List<FileInfo> fileInfos) {
		return fileInfoRepository.saveAll(fileInfos);
	}

	void delete(FileInfo fileInfo) {
		fileInfoRepository.delete(fileInfo);
	}

	protected String generateRandomFilename(ImageType type, String basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
