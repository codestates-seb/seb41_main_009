package com.codestates.hobby.global.config.support;

import org.springframework.data.domain.PageRequest;

import lombok.Getter;

@Getter
public class CustomPageRequest {
	private final int page;
	private final int size;

	public CustomPageRequest(int page, int size) {
		if (page < 1)
			throw new IllegalArgumentException("Page must be greater than zero.");
		if (size < 1)
			throw new IllegalArgumentException("Size must be greater than zero.");

		this.page = page - 1;
		this.size = size;
	}

	public PageRequest to() {
		return PageRequest.of(page, size);
	}
}
