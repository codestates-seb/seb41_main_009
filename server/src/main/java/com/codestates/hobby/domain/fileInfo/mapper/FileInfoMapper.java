package com.codestates.hobby.domain.fileInfo.mapper;

import org.mapstruct.Mapper;

import com.codestates.hobby.domain.fileInfo.dto.FileResponseDto;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {
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
