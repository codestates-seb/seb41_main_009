package com.codestates.hobby.domain.fileInfo.dto;

import com.codestates.hobby.domain.fileInfo.support.FileInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@FileInfoRequest
@NoArgsConstructor
public class FileRequestDto {
	private long size;

	private int index;

	@JsonIgnore
	private boolean isNew;

	private String fileURL;

	private ImageType contentType;

	public FileRequestDto(int index, String fileURL) {
		this.isNew = false;
		this.index = index;
		this.fileURL = fileURL;
	}

	public FileRequestDto(long size, int index, ImageType contentType) {
		this.isNew = true;
		this.size = size;
		this.index = index;
		this.contentType = contentType;
	}
}
