package com.codestates.hobby.domain.category.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

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

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categories = categoryService.findAllGroupsWithHobbies();
		return new ResponseEntity<>(toResponseDto(categories, true), HttpStatus.OK);
	}

	@GetMapping("/groups")
	public ResponseEntity<?> getGroups() {
		List<Category> groups = categoryService.findAllGroups();
		return new ResponseEntity<>(toResponseDto(groups, false), HttpStatus.OK);
	}

	@GetMapping("/groups/{group}")
	public ResponseEntity<?> getCategories(@PathVariable @NotBlank String group) {
		List<Category> categories = categoryService.findAllByGroup(group);
		return new ResponseEntity<>(toResponseDto(categories, false), HttpStatus.OK);
	}

	private MultiResponseDto<?> toResponseDto(List<Category> categories, boolean containHobbies) {
		List<CategoryDto.Response> responses = mapper.categoriesToResponses(categories, containHobbies);
		return new MultiResponseDto<>(new PageImpl<>(responses));
	}
}
