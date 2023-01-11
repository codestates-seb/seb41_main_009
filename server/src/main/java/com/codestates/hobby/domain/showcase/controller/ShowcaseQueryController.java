package com.codestates.hobby.domain.showcase.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShowcaseQueryController {
	private final ShowcaseService showcaseService;

	@GetMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> get(@AuthenticationPrincipal Long memberId, @PathVariable("showcase-id") long showcaseId) {
		Showcase showcase = showcaseService.findById(showcaseId);

		return new ResponseEntity<>(showcase, HttpStatus.OK);
	}

	@GetMapping("/showcases")
	public ResponseEntity<?> getAll(
		@AuthenticationPrincipal Long memberId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "1") int size,
		@RequestParam(defaultValue = "NEWEST") String sort
	) {
		Page<Showcase> showcases = showcaseService.findAll(page, size, sort.equalsIgnoreCase("NEWEST"));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/categories/{category-name}/showcases")
	public ResponseEntity<?> getAllByCategory(
		@AuthenticationPrincipal Long memberId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "1") int size,
		@RequestParam(defaultValue = "NEWEST") String sort,
		@PathVariable("category-name") String category
	) {
		Page<Showcase> showcases = showcaseService.findAllByCategory(category, page, size, sort.equalsIgnoreCase("NEWEST"));

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/members/{member-id}/showcases")
	public ResponseEntity<?> getAllByMember(
		@AuthenticationPrincipal Long authMemberId,
		@PathVariable("member-id") long memberId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "1") int size,
		@RequestParam(defaultValue = "NEWEST") String sort
	) {
		Page<Showcase> showcases = showcaseService.findAllByMember(memberId, page, size, sort.equalsIgnoreCase("NEWEST"));

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
