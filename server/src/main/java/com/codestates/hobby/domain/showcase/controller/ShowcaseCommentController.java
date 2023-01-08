package com.codestates.hobby.domain.showcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases")
public class ShowcaseCommentController {
	@PostMapping("/{showcase-id}/comments")
	public ResponseEntity<?> post(@PathVariable("showcase-id") long showcaseId) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/{showcase-id}/comments/{comment-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId
	) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{showcase-id}/comments/{comment-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId
	) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{showcase-id}/comments")
	public ResponseEntity<?> getAll(@PathVariable("showcase-id") long showcaseId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
