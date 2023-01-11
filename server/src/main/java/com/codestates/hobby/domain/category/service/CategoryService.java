package com.codestates.hobby.domain.category.service;

import java.util.List;

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

	public List<Category> findAll() {
		return null;
	}

	public List<Category> findAllByGroup(String group) {
		return null;
	}

	public List<Category> findAllGroups() {
		return null;
	}
}
