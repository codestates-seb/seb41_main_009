package com.codestates.hobby.domain.notifcation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.global.config.support.CustomPageRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
	// 알람 목록 조회
	@GetMapping
	public ResponseEntity<?> getAll(@AuthenticationPrincipal Long memberId, CustomPageRequest pageRequest) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 알람 개수만 조회
	@GetMapping("/counts")
	public ResponseEntity<?> getCounts(@AuthenticationPrincipal Long memberId) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 알람 제거
	@DeleteMapping("/{notification-id}")
	public ResponseEntity<?> check(
		@PathVariable("notification-id") long notificationId,
		@AuthenticationPrincipal Long memberId
	) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
