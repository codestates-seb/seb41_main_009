package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

@Service
@Profile("!(gcs | aws)")
public class MockFileInfoService extends FileInfoService {
	MockFileInfoService(FileInfoRepository fileInfoRepository) {
		super(fileInfoRepository);
	}

	@Override
	public SignedURL generateSignedURL(ImageType type, BasePath basePath) {
		return new SignedURL(
			"SignedURL",
			String.join("/", basePath.toString(), type.toContentType()),
			type);
	}

	@Override
	public void delete(List<FileInfo> fileInfos) {
		fileInfoRepository.deleteAll(fileInfos);
	}
}
