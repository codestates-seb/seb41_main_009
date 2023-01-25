package com.codestates.hobby.domain.showcase.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.global.config.support.InfiniteScrollRequest;
import com.codestates.hobby.global.dto.InfinitePageInfo;
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
		Showcase showcase = showcaseService.findByIdUsingFetch(showcaseId);

		ShowcaseDto.Response response = mapper.showcaseToResponse(showcase);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/showcases")
	public ResponseEntity<?> getAll(InfiniteScrollRequest isRequest) {
		Slice<Showcase> showcases = showcaseService.findAll(isRequest);

		return toResponseEntity(showcases);
	}

	@GetMapping("/categories/{category-name}/showcases")
	public ResponseEntity<?> getAllByCategory(
		@PathVariable("category-name") String category,
		InfiniteScrollRequest isRequest
	) {
		Slice<Showcase> showcases = showcaseService.findAllByCategory(category, isRequest);

		return toResponseEntity(showcases);
	}

	@GetMapping("/members/{member-id}/showcases")
	public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId, InfiniteScrollRequest isRequest) {
		Slice<Showcase> showcases = showcaseService.findAllByMember(memberId, isRequest);

		return toResponseEntity(showcases);
	}

	private ResponseEntity<?> toResponseEntity(Slice<Showcase> showcases) {
		Slice<ShowcaseDto.SimpleResponse> responses = showcases.map(mapper::showcaseToSimpleResponse);

		InfinitePageInfo info = new InfinitePageInfo(responses.hasNext());

		return new ResponseEntity<>(new MultiResponseDto<>(responses.getContent(), info), HttpStatus.OK);
	}
}
