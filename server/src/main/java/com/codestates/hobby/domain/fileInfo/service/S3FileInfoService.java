package com.codestates.hobby.domain.fileInfo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;

@Service
@Profile("aws")
public class S3FileInfoService extends FileInfoService {
	// https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html

	public S3FileInfoService(FileInfoRepository fileInfoRepository) {
		super(fileInfoRepository);
	}

	@Override
	public SignedURL generateSignedURL(ImageType type, String basePath) {
		return null;
	}
}
