package com.codestates.hobby.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
	private int page;
	private int size;
	private int totalPages;
	private long totalElements;
}
