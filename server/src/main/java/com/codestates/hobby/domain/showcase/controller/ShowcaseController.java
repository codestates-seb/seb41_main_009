package com.codestates.hobby.domain.showcase.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.global.config.support.InfiniteScrollRequest;
import com.codestates.hobby.global.dto.InfinitePageInfo;
import com.codestates.hobby.global.dto.MultiResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ShowcaseController {
	private final ShowcaseService showcaseService;
	private final ShowcaseMapper mapper;

	@PostMapping("/showcases")
	public ResponseEntity<?> post(@AuthenticationPrincipal Member loginMember, @RequestBody @Valid ShowcaseDto.Post post) {
		post.setMemberId(loginMember.getId());

		Showcase showcase = showcaseService.post(post);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.CREATED);
	}

	@PatchMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Member loginMember,
		@RequestBody @Valid ShowcaseDto.Patch patch
	) {
		patch.setMemberId(loginMember.getId());
		patch.setShowcaseId(showcaseId);

		Showcase showcase = showcaseService.update(patch);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.OK);
	}

	@DeleteMapping("/showcases/{showcase-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Member loginMember
	) {
		showcaseService.delete(loginMember.getId(), showcaseId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// -------------- [Query] --------------

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

	@GetMapping("/showcases/search")
	public ResponseEntity<?> search(@RequestParam String query, InfiniteScrollRequest isRequest) {
		Slice<Showcase> showcases = showcaseService.search(query, isRequest);

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
