package com.codestates.hobby.domain.showcase.controller;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.showcase.dto.ShowcaseCommentDto;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseCommentMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseCommentService;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases/{showcase-id}/comments")
public class ShowcaseCommentController {
	private final ShowcaseCommentService commentService;
	private final ShowcaseCommentMapper mapper;

	@GetMapping
	public ResponseEntity<?> getAll(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Long memberId,
		CustomPageRequest pageRequest
	) {
		Page<ShowcaseCommentDto.Response> comments
			= commentService.findAll(showcaseId, pageRequest.to()).map(mapper::entityToResponse);

		comments.forEach(response -> mapper.setProperties(response, memberId));

		return new ResponseEntity<>(new MultiResponseDto<>(comments), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> post(
		@PathVariable("showcase-id") long showcaseId,
		@AuthenticationPrincipal Long memberId,
		@RequestBody ShowcaseCommentDto.Post post
	) {
		post.setProperties(showcaseId, memberId);

		ShowcaseComment comment = commentService.comment(post);

		return new ResponseEntity<>(comment.getId(), HttpStatus.CREATED);
	}

	@PatchMapping("/{comment-id}")
	public ResponseEntity<?> patch(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId,
		@AuthenticationPrincipal Long memberId,
		@RequestBody ShowcaseCommentDto.Patch patch
	) {
		patch.setProperties(memberId, showcaseId, commentId);

		commentService.update(patch);

		return new ResponseEntity<>(commentId, HttpStatus.OK);
	}

	@DeleteMapping("/{comment-id}")
	public ResponseEntity<?> delete(
		@PathVariable("showcase-id") long showcaseId,
		@PathVariable("comment-id") long commentId,
		@AuthenticationPrincipal Long memberId
	) {
		commentService.delete(memberId, showcaseId, commentId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
