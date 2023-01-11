package com.codestates.hobby.domain.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.codestates.hobby.domain.category.dto.CategoryDto;
import com.codestates.hobby.domain.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryDto.Response categoryToResponse(Category category);

	List<CategoryDto.Response> categoriesToResponses(List<Category> categories);
}
