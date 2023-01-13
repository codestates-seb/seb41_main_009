package com.codestates.hobby.domain.showcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases")
public class ShowcaseCommendController {
	private final ShowcaseService showcaseService;

	@PostMapping
	public ResponseEntity<?> post(@AuthenticationPrincipal Long memberId, @RequestBody ShowcaseDto.Post post) {
		post.setMemberId(memberId);

		Showcase showcase = showcaseService.post(post);

		return new ResponseEntity<>(showcase.getId(), HttpStatus.CREATED);
	}

	@PatchMapping("/{showcase-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Long memberId,
		@RequestBody ShowcaseDto.Patch patch
	) {
		patch.setProperties(memberId, showcaseId);

		Showcase showcase = showcaseService.update(patch);

		return new ResponseEntity<>(showcase.getId(), HttpStatus.OK);
	}

	@DeleteMapping("/{showcase-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Long memberId
	) {
		showcaseService.delete(memberId, showcaseId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
