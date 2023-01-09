package com.codestates.hobby.domain.showcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

// TODO: return STATUS.NO_CONTENT if has no content

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShowcaseQueryController {
	@GetMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> get(@PathVariable("showcase-id") long showcaseId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/showcases")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/categories/{category-name}/showcases")
	public ResponseEntity<?> getAllByCategory(@PathVariable("category-name") String category) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/members/{member-id}/showcases")
	public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
