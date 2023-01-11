package com.codestates.hobby.domain.category.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CategoryDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String korName;
		private String engName;
		private List<Response> categories;
	}
}
