package com.codestates.hobby.domain.showcase.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.global.config.support.InfiniteScrollRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShowcaseQueryController {
	private final ShowcaseService showcaseService;
	private final ShowcaseMapper mapper;

	@GetMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> get(@PathVariable("showcase-id") long showcaseId) {
		Showcase showcase = showcaseService.findById(showcaseId);

		ShowcaseDto.Response response = mapper.showcaseToResponse(showcase);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/showcases")
	public ResponseEntity<?> getAll(InfiniteScrollRequest isRequest) {
		Page<Showcase> showcases = showcaseService.findAll(isRequest);

		return toResponseEntity(showcases);
	}

	@GetMapping("/categories/{category-name}/showcases")
	public ResponseEntity<?> getAllByCategory(
		@SessionAttribute(required = false) Member loginMember,
		@PathVariable("category-name") String category,
		InfiniteScrollRequest isRequest
	) {
		Page<Showcase> showcases = showcaseService.findAllByCategory(category, isRequest);

		return toResponseEntity(showcases);
	}

	@GetMapping("/members/{member-id}/showcases")
	public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId, InfiniteScrollRequest isRequest) {
		Page<Showcase> showcases = showcaseService.findAllByMember(memberId, isRequest);

		return toResponseEntity(showcases);
	}

	private ResponseEntity<?> toResponseEntity(Page<Showcase> showcases) {
		Page<ShowcaseDto.SimpleResponse> responses = showcases.map(mapper::showcaseToSimpleResponse);

		return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
	}
}
