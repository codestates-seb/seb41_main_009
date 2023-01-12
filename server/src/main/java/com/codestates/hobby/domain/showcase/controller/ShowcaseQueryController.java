package com.codestates.hobby.domain.showcase.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShowcaseQueryController {
	private final ShowcaseService showcaseService;
	private final ShowcaseMapper mapper;

	@GetMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> get(@AuthenticationPrincipal Long memberId, @PathVariable("showcase-id") long showcaseId) {
		Showcase showcase = showcaseService.findById(showcaseId);

		ShowcaseDto.Response response = mapper.showcaseToResponse(showcase);
		mapper.setProperties(response, memberId);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/showcases")
	public ResponseEntity<?> getAll(
		@AuthenticationPrincipal Long memberId,
		CustomPageRequest pageRequest
	) {
		Page<Showcase> showcases = showcaseService.findAll(pageRequest.to());

		return toResponseEntity(showcases, memberId);
	}

	@GetMapping("/categories/{category-name}/showcases")
	public ResponseEntity<?> getAllByCategory(
		@PathVariable("category-name") String category,
		@AuthenticationPrincipal Long memberId,
		CustomPageRequest pageRequest
	) {
		Page<Showcase> showcases =
			showcaseService.findAllByCategory(category, pageRequest.to());

		return toResponseEntity(showcases, memberId);
	}

	@GetMapping("/members/{member-id}/showcases")
	public ResponseEntity<?> getAllByMember(
		@AuthenticationPrincipal Long authMemberId,
		@PathVariable("member-id") long memberId,
		CustomPageRequest pageRequest
	) {
		Page<Showcase> showcases =
			showcaseService.findAllByMember(memberId, pageRequest.to());

		return toResponseEntity(showcases, authMemberId);
	}

	private ResponseEntity<?> toResponseEntity(Page<Showcase> showcases, Long memberId) {
		if (showcases.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			Page<ShowcaseDto.SimpleResponse> responses = showcases.map(mapper::showcaseToSimpleResponse);

			responses.forEach(showcase -> mapper.setProperties(showcase, memberId));

			return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
		}
	}
}
