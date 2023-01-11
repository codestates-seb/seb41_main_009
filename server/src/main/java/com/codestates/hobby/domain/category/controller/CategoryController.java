package com.codestates.hobby.domain.category.controller;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.category.dto.CategoryDto;
import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.mapper.CategoryMapper;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.global.dto.MultiResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;
	private final CategoryMapper mapper;

	// 카테고리 그룹 목록을 조회한다.
	@GetMapping("/groups")
	public ResponseEntity<?> getGroups() {
		List<Category> groups = categoryService.findAllGroups();
		return new ResponseEntity<>(toResponseDto(groups), HttpStatus.OK);
	}

	// 특정 그룹의 카테고리 목록을 조회한다.
	@GetMapping("/groups/{group}")
	public ResponseEntity<?> getCategories(@PathVariable String group) {
		List<Category> categories = categoryService.findAllByGroup(group);
		return new ResponseEntity<>(toResponseDto(categories), HttpStatus.OK);
	}

	// 모든 카테고리 목록을 조회한다.
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(toResponseDto(categories), HttpStatus.OK);
	}

	private MultiResponseDto<?> toResponseDto(List<Category> categories) {
		List<CategoryDto.Response> responses = mapper.categoriesToResponses(categories);
		return new MultiResponseDto<>(new PageImpl<>(responses));
	}
}
