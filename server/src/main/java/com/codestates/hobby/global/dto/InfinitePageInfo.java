package com.codestates.hobby.global.dto;

import lombok.Getter;

@Getter
public class InfinitePageInfo extends PageInfo{
	private final boolean hasNext;

	public InfinitePageInfo(boolean hasNext) {
		this.hasNext = hasNext;
	}
}
