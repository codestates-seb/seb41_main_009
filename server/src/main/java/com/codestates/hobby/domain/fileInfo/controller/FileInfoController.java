package com.codestates.hobby.domain.fileInfo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.fileInfo.dto.SignedURL;
import com.codestates.hobby.domain.fileInfo.service.FileInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/{base-path}")
public class FileInfoController {
	private final FileInfoService fileInfoService;

	@PostMapping("/files")
	public ResponseEntity<?> generateURL(@PathVariable("base-path") BasePath basePath, @RequestBody ImageType type) {
		SignedURL signedURL = fileInfoService.generateSignedURL(type, basePath);

		return new ResponseEntity<>(signedURL, HttpStatus.CREATED);
	}
}
