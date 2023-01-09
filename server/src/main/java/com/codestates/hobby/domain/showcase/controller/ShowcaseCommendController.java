package com.codestates.hobby.domain.showcase.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases")
public class ShowcaseCommendController {
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> post(
		@RequestPart(value = "imgFiles") List<MultipartFile> imgFiles,
		@RequestPart(value = "request") ShowcaseDto.Post post
	) {

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping(
		value = "/{showcase-id}",
		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@RequestPart(value = "imgFiles") List<MultipartFile> imgFiles,
		@RequestPart(value = "request") ShowcaseDto.Patch patch
	) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{showcase-id}")
	public ResponseEntity<?> delete(@PathVariable("showcase-id") long showcaseId) {

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
