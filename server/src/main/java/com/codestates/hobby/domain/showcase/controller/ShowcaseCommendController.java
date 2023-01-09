package com.codestates.hobby.domain.showcase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/showcases")
public class ShowcaseCommendController {
	@PostMapping
	public ResponseEntity<?> post() {
		// 1. 쇼케이스를 생성한다. (회원, 이미지, 내용, 제목)
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/{showcase-id}")
	public ResponseEntity<?> patch(@PathVariable("showcase-id") long showcaseId) {
		// 2. 쇼케이스를 수정한다. (이미지, 내용, 제목)
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{showcase-id}")
	public ResponseEntity<?> delete(@PathVariable("showcase-id") long showcaseId) {
		// 3. 쇼케이스를 삭제한다. (회원)
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
