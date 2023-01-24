package com.codestates.hobby.global.config.support;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class InfiniteScrollArgumentResolver implements HandlerMethodArgumentResolver {
	private static final int DEFAULT_SIZE = 9;
	private static final int MAX_SIZE = DEFAULT_SIZE * 2;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(InfiniteScrollRequest.class);
	}

	@Override
	public Object resolveArgument(
		MethodParameter methodParameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) {
		String pageStr = webRequest.getParameter("offset");
		String sizeStr = webRequest.getParameter("size");

		long offset = NumberUtils.toLong(pageStr, -1);
		int size = Math.min(MAX_SIZE, NumberUtils.toInt(sizeStr, DEFAULT_SIZE));

		return new InfiniteScrollRequest(offset, size);
	}
}
