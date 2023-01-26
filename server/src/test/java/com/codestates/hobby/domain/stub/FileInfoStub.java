package com.codestates.hobby.domain.stub;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;

import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.dto.FileResponseDto;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

public class FileInfoStub {
	private static final ImageType[] types = ImageType.values();

	public static FileRequestDto createRequest(int index) {
		return new FileRequestDto(1000 * 100, index, types[RandomUtils.nextInt(0, types.length)]);
	}

	public static FileResponseDto createResponse() {
		return createResponse(createRequest(0));
	}

	public static FileResponseDto createResponse(FileRequestDto req) {
		return FileResponseDto.of(
			req,
			"http://domain.com/bucket/basepath/file." + req.getContentType().getExtension(),
			"SignedURL");
	}

	public static List<FileRequestDto> createRequests(int bound, int size) {
		return IntStream.range(bound, bound + size)
			.mapToObj(FileInfoStub::createRequest)
			.collect(Collectors.toList());
	}

	public static List<FileResponseDto> createResponses(List<FileRequestDto> reqs) {
		return reqs.stream().map(FileInfoStub::createResponse).collect(Collectors.toList());
	}

	public static List<FileResponseDto> createResponses(int bound, int size) {
		return IntStream.range(bound, bound + size)
			.mapToObj(i -> createResponse(createRequest(i)))
			.collect(Collectors.toList());
	}

	public static FileResponseDto createUpdateResponse() {
		return new FileResponseDto(
			1,
			"http://domain.com/bucket/basepath/file.png",
			"SignedURL",
			ImageType.PNG);
	}

	public static FileInfo createEntity(boolean isNew) {
		String fileURL = "https://storage.cloud.google.com/intorest-images/basepath/filename.png";
		return isNew
			? new FileInfo(fileURL, "SignedURL", 0)
			: new FileInfo(ShowcaseStub.createShowcase(), fileURL, 0);
	}
}
