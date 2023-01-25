package com.codestates.hobby.global.config.support;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class InfiniteScrollRequest {
	private final long offset;
	private final int size;

	public InfiniteScrollRequest() {
		this(-1, 9);
	}

	public InfiniteScrollRequest(long offset, int size) {
		this.offset = offset;
		this.size = size;
	}

	public PageRequest of() {
		return PageRequest.of(0, size, Sort.by("id").descending());
	}

	public long getOffset() {
		return offset;
	}
}
