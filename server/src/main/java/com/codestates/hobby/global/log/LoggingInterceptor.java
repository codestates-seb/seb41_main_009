package com.codestates.hobby.global.log;

import static org.apache.commons.lang3.StringUtils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) return true;

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Class<?> beanType = handlerMethod.getBeanType();

		ThreadContext.put("resource", beanType.getSimpleName().replaceAll("Controller", ""));
		ThreadContext.put("command", handlerMethod.getMethod().getName());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadContext.put("principal", defaultString(request.getRemoteUser(), "anonymous"));
	}
}
