package com.codestates.hobby.domain.subscription.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.global.config.support.CustomPageRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SubscriptionController {
	// 다른 회원을 구독
	@PostMapping("/subscriptions/members/{member-id}")
	public ResponseEntity<?> subscribe(
		@AuthenticationPrincipal Long memberId,
		@PathVariable("member-id") long targetId
	) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// 구독한 회원을 구독취소
	@DeleteMapping("/subscriptions/members/{member-id}")
	public ResponseEntity<?> cancel(@AuthenticationPrincipal Long memberId, @PathVariable("member-id") long targetId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// 회원이 구독중인 회원목록 조회
	@GetMapping("/subscriptions")
	public ResponseEntity<?> getSubscriptions(@AuthenticationPrincipal Long memberId, CustomPageRequest pageRequest) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 회원을 구독하는 회원목록 조회
	@GetMapping("/subscribers")
	public ResponseEntity<?> getSubscribers(@AuthenticationPrincipal Long memberId, CustomPageRequest pageRequest) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 회원이 구독중인 회원들의 쇼케이스 리스트
	@GetMapping("/subscriptions/showcases")
	public ResponseEntity<?> getShowcases(@AuthenticationPrincipal Long memberId, CustomPageRequest pageRequest) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 구독중인 회원들의 포스트 리스트
	@GetMapping("/subscriptions/posts")
	public ResponseEntity<?> getPosts(@AuthenticationPrincipal Long memberId, CustomPageRequest pageRequest) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
