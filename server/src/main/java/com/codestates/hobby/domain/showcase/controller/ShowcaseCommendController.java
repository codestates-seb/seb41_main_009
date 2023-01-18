package com.codestates.hobby.domain.showcase.controller;

import javax.validation.Valid;

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
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases")
public class ShowcaseCommendController {
	private final ShowcaseService showcaseService;
	private final ShowcaseMapper mapper;

	@PostMapping
	public ResponseEntity<?> post(@AuthenticationPrincipal Long memberId, @RequestBody @Valid ShowcaseDto.Post post) {
		post.setMemberId(memberId);

		Showcase showcase = showcaseService.post(post);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.CREATED);
	}

	@PatchMapping("/{showcase-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Long memberId,
		@RequestBody @Valid ShowcaseDto.Patch patch
	) {
		patch.setMemberId(memberId);
		patch.setShowcaseId(showcaseId);

		Showcase showcase = showcaseService.update(patch);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.OK);
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
