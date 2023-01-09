package com.codestates.hobby.domain.subscription.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SubscriptionController {
	// 다른 회원을 구독
	@PostMapping("/subscriptions/members/{member-id}")
	public ResponseEntity<?> subscribe(@PathVariable("member-id") long memberId) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// 구독한 회원을 구독취소
	@DeleteMapping("/subscriptions/members/{member-id}")
	public ResponseEntity<?> cancel(@PathVariable("member-id") long memberId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// 회원이 구독중인 회원목록 조회
	@GetMapping("/subscriptions")
	public ResponseEntity<?> getSubscriptions() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 회원을 구독하는 회원목록 조회
	@GetMapping("/subscribers")
	public ResponseEntity<?> getSubscribers() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
