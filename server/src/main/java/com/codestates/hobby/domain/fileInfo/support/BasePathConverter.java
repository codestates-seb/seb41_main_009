package com.codestates.hobby.domain.fileInfo.support;

import org.springframework.core.convert.converter.Converter;

import com.codestates.hobby.domain.fileInfo.dto.BasePath;

public class BasePathConverter implements Converter<String, BasePath> {
	@Override
	public BasePath convert(String str) {
		return BasePath.valueOf(str.toUpperCase());
	}
}
