package com.codestates.hobby.domain.showcase.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codestates.hobby.domain.member.entity.Member;
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
	public ResponseEntity<?> post(@SessionAttribute Member loginMember, @RequestBody @Valid ShowcaseDto.Post post) {
		post.setMemberId(loginMember.getId());

		Showcase showcase = showcaseService.post(post);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.CREATED);
	}

	@PatchMapping("/{showcase-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@SessionAttribute Member loginMember,
		@RequestBody @Valid ShowcaseDto.Patch patch
	) {
		patch.setMemberId(loginMember.getId());
		patch.setShowcaseId(showcaseId);

		Showcase showcase = showcaseService.update(patch);

		return new ResponseEntity<>(mapper.showcaseToCommendResponse(showcase), HttpStatus.OK);
	}

	@DeleteMapping("/{showcase-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@SessionAttribute Member loginMember
	) {
		showcaseService.delete(loginMember.getId(), showcaseId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
