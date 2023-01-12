package com.codestates.hobby.domain.fileInfo.dto;

public enum BasePath {
	POSTS, SERIES, SHOWCASES, MEMBERS;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
