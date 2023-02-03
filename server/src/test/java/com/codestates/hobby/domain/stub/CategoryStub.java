package com.codestates.hobby.domain.stub;

import java.util.List;

import com.codestates.hobby.domain.category.dto.CategoryDto;
import com.codestates.hobby.domain.category.entity.Category;

public class CategoryStub {
	public static CategoryDto.Response groupResponse() {
		CategoryDto.Response group = new CategoryDto.Response();
		CategoryDto.Response cat1 = new CategoryDto.Response();
		CategoryDto.Response cat2 = new CategoryDto.Response();

		group.setId(1L);
		group.setKorName("운동");
		group.setEngName("EXERCISE");
		group.setCategories(List.of(cat1, cat2));

		cat1.setId(2L);
		cat2.setId(3L);
		cat1.setKorName("조깅");
		cat2.setKorName("수영");
		cat1.setEngName("JOGGIN");
		cat2.setEngName("SWIMMING");

		return group;
	}

	public static Category group() {
		return Category.createParent("운동", "sports");
	}

	public static Category hobby() {
		return Category.createChild("축구", "soccer", group());
	}
}
