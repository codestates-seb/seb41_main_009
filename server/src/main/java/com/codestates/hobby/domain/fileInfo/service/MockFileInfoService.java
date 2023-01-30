package com.codestates.hobby.domain.fileInfo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;

@Service
@Profile("local")
public class MockFileInfoService extends FileInfoService {
	MockFileInfoService(FileInfoRepository fileInfoRepository) {
		super(fileInfoRepository);
	}

	@Override
	public FileInfo generateSignedURL(FileRequestDto request, BasePath basePath) {
		if (request.isNew()) {
			return new FileInfo(
				"http://domain.com/bucket/" + generateRandomFilename(request.getContentType(), basePath),
				"signedURL",
				request.getIndex()
			);
		}
		return fileInfoRepository.findByFileURL_FileUrl(request.getFileURL())
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_SHOWCASE));
	}

}
