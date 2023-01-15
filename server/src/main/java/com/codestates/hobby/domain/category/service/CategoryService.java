package com.codestates.hobby.domain.category.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
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

	@Cacheable(value = "category", key = "#categoryId")
	public Category findById(long categoryId) {
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new IllegalArgumentException("Not found category for " + categoryId));
	}

	@Cacheable(value = "category", key = "#name")
	public Category findHobbyByName(String name) {
		return categoryRepository.findByGroupIsNotNullAndName(name.toLowerCase())
			.orElseThrow(() -> new IllegalArgumentException("Not found hobby for " + name));
	}

	@Cacheable(value = "category", key = "#name")
	public Category findGroupByName(String name) {
		return categoryRepository.findByGroupIsNullAndName(name.toLowerCase())
			.orElseThrow(() -> new IllegalArgumentException("Not found group for " + name));
	}

	@Cacheable(value = "category", key = "'findAll'")
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Cacheable(value = "category", key = "'findAllGroups'")
	public List<Category> findAllGroups() {
		return categoryRepository.findAllByGroupIsNull();
	}

	@Cacheable(value = "category", key = "'findAllBy' + #group")
	public List<Category> findAllByGroup(String group) {
		return categoryRepository.findAllByGroup(findGroupByName(group));
	}
}