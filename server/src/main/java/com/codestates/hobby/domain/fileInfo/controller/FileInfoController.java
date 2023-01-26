package com.codestates.hobby.domain.fileInfo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.mapper.FileInfoMapper;
import com.codestates.hobby.domain.fileInfo.service.FileInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/{base-path}")
public class FileInfoController {
	private final FileInfoService fileInfoService;
	private final FileInfoMapper mapper;

	@PostMapping("/files")
	public ResponseEntity<?> generateURL(
		@PathVariable("base-path") BasePath basePath,
		@RequestBody @Valid FileRequestDto request
	) {
		FileInfo fileInfo = fileInfoService.generateSignedURL(request, basePath);

		return new ResponseEntity<>(mapper.fileInfoToResponse(fileInfo), HttpStatus.CREATED);
	}
}
