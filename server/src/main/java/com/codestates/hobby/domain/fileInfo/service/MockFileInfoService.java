package com.codestates.hobby.domain.fileInfo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

@Service
@Profile("!(gcs | aws)")
public class MockFileInfoService extends FileInfoService {
	MockFileInfoService(FileInfoRepository fileInfoRepository) {
		super(fileInfoRepository);
	}

	@Override
	public FileInfo generateSignedURL(FileRequestDto request, BasePath basePath) {
		return new FileInfo("http://domain.com/bucket/basepath/file.png", "signedURL", request.getIndex());
	}

}
