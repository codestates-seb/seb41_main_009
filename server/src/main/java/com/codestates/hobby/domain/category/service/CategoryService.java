package com.codestates.hobby.domain.category.service;

import java.util.List;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
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
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_CATEGORY));
	}

	public Category findHobbyByName(String name) {
		return categoryRepository.findByGroupIsNotNullAndName(name.toLowerCase())
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_CATEGORY));
	}

	public Category findGroupByName(String name) {
		return categoryRepository.findByGroupIsNullAndName(name.toLowerCase())
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_CATEGORY));
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public List<Category> findAllGroupsWithHobbies() {
		return categoryRepository.findAllGroupsWithHobbies();
	}

	public List<Category> findAllGroups() {
		return categoryRepository.findAllByGroupIsNull();
	}

	public List<Category> findAllByGroup(String group) {
		return categoryRepository.findAllByGroup(findGroupByName(group));
	}
}