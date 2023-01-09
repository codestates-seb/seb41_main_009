package com.codestates.hobby.domain.showcase.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases/{showcase-id}/comments")
public class ShowcaseCommentController {
	@GetMapping
	public ResponseEntity<?> getAll(@PathVariable("showcase-id") long showcaseId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> post(
		@PathVariable("showcase-id") long showcaseId,
		@RequestBody @NotBlank String content
	) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/{comment-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId,
		@RequestBody @NotBlank String content
	) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{comment-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId
	) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
