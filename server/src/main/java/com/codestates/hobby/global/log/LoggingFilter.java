package com.codestates.hobby.global.log;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		if (request.getRequestURI().startsWith("/favicon")) {
			chain.doFilter(request, response);
			return;
		}

		long requestTime = System.currentTimeMillis();

		logBeforeProcess(request);

		chain.doFilter(request, response);

		logAfterProcess(request, response, requestTime);
	}

	private void logBeforeProcess(HttpServletRequest request) {
		ThreadContext.put("type", "debug");
		ThreadContext.put("logId", UUID.randomUUID().toString());
		if (request.getRequestURI().startsWith("/login")) {
			ThreadContext.put("resource", "auth");
			ThreadContext.put("command", "login");
		}
	}

	private void logAfterProcess(HttpServletRequest request, HttpServletResponse response, long requestTime) {
		String queryString = isBlank(request.getQueryString()) ? "" : ("?" + request.getQueryString());
		ThreadContext.put("type", "access");

		setRequestProperties(request);
		setResponseProperties(response, requestTime);

		log.info("RequestURI: [{}] {}{}", request.getMethod(), request.getRequestURI(), queryString);
		ThreadContext.clearAll();
	}

	private void setRequestProperties(HttpServletRequest request) {
		String requestedSessionId = defaultString(request.getRequestedSessionId(), "null");
		String remoteAddr = defaultString(request.getRemoteAddr(), request.getHeader("X-Forwarded-For"));
		String contentType = defaultString(request.getContentType(), "null");
		String queryString = isBlank(request.getQueryString()) ? "" : ("?" + request.getQueryString());

		ThreadContext.put("contentType", contentType);
		ThreadContext.put("remoteAddr", remoteAddr);
		ThreadContext.put("requestUrl", request.getRequestURL().toString() + queryString);
		ThreadContext.put("requestMethod", request.getMethod());
		ThreadContext.put("requestedSessionId", requestedSessionId);
		ThreadContext.put("userAgent", request.getHeader("User-Agent"));
	}

	private void setResponseProperties(HttpServletResponse response, long requestTime) {
		ThreadContext.put("status", String.valueOf(response.getStatus()));
		ThreadContext.put("latency", String.valueOf(System.currentTimeMillis() - requestTime));
		ThreadContext.put("responseSize", String.valueOf(response.getBufferSize()));
	}
}
