package com.codestates.hobby.domain.fileInfo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Profile("aws")
@RequiredArgsConstructor
public class S3FileInfoService {
	// https://docs.aws.amazon.com/ko_kr/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html
}
