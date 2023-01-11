package com.codestates.hobby.domain.fileInfo.service;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;

import lombok.RequiredArgsConstructor;

@Service
@Profile("gcs")
@RequiredArgsConstructor
public class GCSFileInfoService implements FileInfoService {
	private final Storage storage;

	@Value("${cloud.storage.gcs.bucket-name:intorest-imgaes}")
	private String bucketName;

	@Override
	public Map<String, String> generateSignedURL(ImageType imageType, String basePath) {
		String savedFilename = generateRandomFilename(imageType, basePath);
		BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, savedFilename)).build();
		Map<String, String> headers = Collections.singletonMap("Content-Type", imageType.toContentType());

		URL url = storage.signUrl(
			blobInfo,
			15,
			TimeUnit.MINUTES,
			Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
			Storage.SignUrlOption.withExtHeaders(headers),
			Storage.SignUrlOption.withV4Signature());

		Map<String, String> result = new HashMap<>();
		result.put("url", url.toString());
		result.put("path", String.join("/", bucketName, savedFilename));

		return result;
	}
}
