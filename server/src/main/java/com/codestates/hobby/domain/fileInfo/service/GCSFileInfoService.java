package com.codestates.hobby.domain.fileInfo.service;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;

@Service
@Profile("gcs")
public class GCSFileInfoService extends FileInfoService {
	private final Storage storage;

	@Value("${cloud.storage.gcs.bucket-name}")
	private String bucketName;

	@Value("${cloud.storage.gcs.domain}")
	private String domain;

	@Value("${cloud.storage.gcs.duration}")
	private int duration;

	public GCSFileInfoService(FileInfoRepository fileInfoRepository, Storage storage) {
		super(fileInfoRepository);
		this.storage = storage;
	}

	@Override
	public SignedURL generateSignedURL(ImageType imageType, BasePath basePath) {
		String savedFilename = generateRandomFilename(imageType, basePath.toString());
		String fileUrl;

		do {
			fileUrl = String.join("/", domain, bucketName, savedFilename);
		} while (fileInfoRepository.existsByFileURL(fileUrl));

		BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, savedFilename)).build();
		Map<String, String> headers = Collections.singletonMap("Content-Type", imageType.toContentType());

		URL url = storage.signUrl(
			blobInfo,
			duration,
			TimeUnit.MINUTES,
			Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
			Storage.SignUrlOption.withExtHeaders(headers),
			Storage.SignUrlOption.withContentType(),
			Storage.SignUrlOption.withV4Signature());

		return new SignedURL(url.toString(), fileUrl, imageType);
	}

	@Override
	public void delete(FileInfo fileInfo) {
		if (!storage.delete(BlobId.of(fileInfo.getBucket(), fileInfo.getFilename()))) {
			String message =
				"FileInfo [" + fileInfo.getId() + "] was not deleted. (url: " + fileInfo.getFileURL() + ")";
			throw new RuntimeException(message);
		}

		super.delete(fileInfo);
	}

	public void delete(List<FileInfo> fileInfos) {
		fileInfos.forEach(this::delete);
	}
}
