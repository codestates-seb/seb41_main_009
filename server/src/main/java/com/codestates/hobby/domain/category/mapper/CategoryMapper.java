package com.codestates.hobby.domain.category.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.codestates.hobby.domain.category.dto.CategoryDto;
import com.codestates.hobby.domain.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	default String map(Category value) {
		return value.getKorName();
	}

	default List<CategoryDto.Response> categoriesToResponses(List<Category> categories, boolean containHobbies) {
		return categories.stream()
			.map(category -> categoryToResponse(category, containHobbies))
			.collect(Collectors.toList());
	}

	default CategoryDto.Response categoryToResponse(Category category, boolean containHobbies) {
		CategoryDto.Response response = new CategoryDto.Response();
		response.setId(category.getId());
		response.setKorName(category.getKorName());
		response.setEngName(category.getEngName());

		return containHobbies && category.isGroup()
			? categoryToResponse(response, category)
			: response;
	}

	default CategoryDto.Response categoryToResponse(CategoryDto.Response response, Category category) {
		response.setCategories(
			category.getCategories()
				.stream()
				.map(category1 -> categoryToResponse(category1, false))
				.collect(Collectors.toList())
		);
		return response;
	}
}
