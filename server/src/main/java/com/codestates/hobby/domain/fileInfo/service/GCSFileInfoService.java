package com.codestates.hobby.domain.fileInfo.service;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
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

	@Value("${cloud.storage.gcs.bucket-name}")
	private String bucketName;

	@Value("${cloud.storage.gcs.domain}")
	private String domain;

	@Value("${cloud.storage.gcs.duration}")
	private int duration;

	@Override
	public SignedURL generateSignedURL(ImageType imageType, String basePath) {
		String savedFilename = generateRandomFilename(imageType, basePath);
		BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, savedFilename)).build();
		Map<String, String> headers = Collections.singletonMap("Content-Type", imageType.toContentType());

		URL url = storage.signUrl(
			blobInfo,
			duration,
			TimeUnit.MINUTES,
			Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
			Storage.SignUrlOption.withExtHeaders(headers),
			Storage.SignUrlOption.withV4Signature());

		return new SignedURL(url.toString(), String.join(domain, bucketName, savedFilename));
	}

	@Override
	public void delete(FileInfo fileInfo) {
		if (!storage.delete(BlobId.of(fileInfo.getBucket(), fileInfo.getFilename()))) {
			String message =
				"FileInfo [" + fileInfo.getId() + "] was not deleted. (url: " + fileInfo.getFileURL() + ")";
			throw new RuntimeException(message);
		}
	}

	@Override
	public void delete(List<FileInfo> fileInfos) {
		fileInfos.forEach(this::delete);
	}
}
