package com.codestates.hobby.domain.fileInfo.service;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

import lombok.RequiredArgsConstructor;

@Service
@Profile("aws")
@RequiredArgsConstructor
public class S3FileInfoService implements FileInfoService {
	// https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html
	
	@Override
	public SignedURL generateSignedURL(ImageType type, String basePath) {
		return null;
	}

	@Override
	public void delete(FileInfo fileInfo) {

	}

	@Override
	public void delete(List<FileInfo> fileInfos) {

	}
}
