package com.codestates.hobby.global.config.support;

import static org.springframework.util.StringUtils.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagingArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(CustomPageRequest.class);
	}

	@Override
	public Object resolveArgument(
		MethodParameter methodParameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) {
		String pageStr = webRequest.getParameter("page");
		String sizeStr = webRequest.getParameter("size");

		// TODO: 컨트롤러마다 기본값이 다름.
		//     : 각자 디폴트 Value를 설정할 수 있어야됨.
		// 1. Flag와 page, size 인터페이스를 만들어서 메서드 핸들러가 실행된 이후 재설정을 강제한다.
		// 2. 제네릭...? -> 제네릭의 실제 타입을 현재 스코프에서 불러올 수 있는 방법을 모르겠음.
		int page = NumberUtils.toInt(pageStr, 1);
		int size = NumberUtils.toInt(sizeStr, 5);

		return new CustomPageRequest(page, size);
	}
}
