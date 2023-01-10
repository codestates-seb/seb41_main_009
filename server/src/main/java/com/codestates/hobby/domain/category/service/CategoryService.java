package com.codestates.hobby.domain.category.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;

@Service
@Transactional(readOnly = true)
public class CategoryService {
	public Category findById(long categoryId) {
		return null;
	}

	public Category findByName(String categoryName) {
		return null;
	}

	public Page<Category> findAll(int page, int size) {
		return null;
	}

	public Page<Category> findAllByGroup(String group, int page, int size) {
		return null;
	}

	public Page<Category> findAllGroups(int page, int size) {
		return null;
	}
}
