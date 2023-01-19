package com.codestates.hobby.domain.fileInfo.mapper;

import static org.springframework.util.StringUtils.*;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.codestates.hobby.domain.fileInfo.dto.FileResponseDto;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
	default List<FileResponseDto> fileInfosToResponses(List<FileInfo> infos) {
		return infos.stream()
			.filter(info -> hasText(info.getSignedURL()))
			.map(this::fileInfoToResponse)
			.collect(Collectors.toList());
	}

	default FileResponseDto fileInfoToResponse(FileInfo fileInfo) {
		return new FileResponseDto(
			fileInfo.getIndex(),
			fileInfo.getFileURL(),
			fileInfo.getSignedURL(),
			fileInfo.getImageType());
	}

	default String map(FileInfo value) {
		return value.getFileURL();
	}
}
