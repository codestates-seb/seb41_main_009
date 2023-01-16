package com.codestates.hobby.domain.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public Category findById(long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new IllegalArgumentException("Not found category for " + categoryId));
	}

	public Category findHobbyByName(String name) {
		return categoryRepository.findByGroupIsNotNullAndName(name.toLowerCase())
			.orElseThrow(() -> new IllegalArgumentException("Not found category for " + name));
	}

	public Category findGroupByName(String name) {
		return categoryRepository.findByGroupIsNullAndName(name.toLowerCase())
			.orElseThrow(() -> new IllegalArgumentException("Not found category for " + name));
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public List<Category> findAllGroups() {
		return categoryRepository.findAllByGroupIsNull();
	}

	public List<Category> findAllByGroup(String group) {
		return categoryRepository.findAllByGroup(findGroupByName(group));
	}
}