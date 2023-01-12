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

	abstract public void delete(List<FileInfo> fileInfos);

	public FileInfo save(FileInfo fileInfo) {
		return fileInfoRepository.save(fileInfo);
	}

	public List<FileInfo> save(List<FileInfo> fileInfos) {
		return fileInfoRepository.saveAll(fileInfos);
	}

	public FileInfo saveByUrl(String url) {
		return save(new FileInfo(url));
	}

	public List<FileInfo> saveAllByUrls(List<String> urls) {
		List<FileInfo> infos = urls.stream().map(FileInfo::new).collect(Collectors.toList());
		return save(infos);
	}

	public void delete(FileInfo fileInfo) {
		fileInfoRepository.delete(fileInfo);
	}

	protected String generateRandomFilename(ImageType type, String basePath) {
		return String.format("%s/%s.%s", basePath, UUID.randomUUID(), type.getExtension());
	}
}
