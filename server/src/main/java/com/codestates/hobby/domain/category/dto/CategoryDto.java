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
		private boolean isGroup;
		private Response group;	// entity -> dto 매핑할 때 순환참조 조심해야됨
		private List<Response> categories;
	}
}
