package com.codestates.hobby.global.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class MultiResponseDto<T> {
	private final List<T> data;
	private final PageInfo pageInfo;

	public MultiResponseDto(Page<T> page) {
		this.data = page.getContent();
		this.pageInfo = new PageInfo(
			page.getNumber() + 1,
			page.getSize(),
			page.getTotalPages(),
			page.getTotalElements()
		);
	}
}
