package com.codestates.hobby.domain.fileInfo.service;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;

import lombok.RequiredArgsConstructor;

@Service
@Profile("aws")
@RequiredArgsConstructor
public class S3FileInfoService implements FileInfoService {
	// https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html

	@Override
	public Map<String, String> generateSignedURL(ImageType type, String basePath) {
		return null;
	}
}
