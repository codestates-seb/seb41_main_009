package com.codestates.hobby.domain.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	// 카테고리 그룹 목록을 조회한다.
	@GetMapping("/groups")
	public ResponseEntity<?> getGroups() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 특정 그룹의 카테고리 목록을 조회한다.
	@GetMapping("/groups/{group}")
	public ResponseEntity<?> getCategories(@PathVariable String group) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 모든 카테고리 목록을 조회한다.
	@GetMapping
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
