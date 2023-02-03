package com.codestates.hobby.global.log;

import static org.apache.commons.lang3.StringUtils.*;

import java.lang.reflect.Type;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice(annotations = RestController.class)
public class RequestBodyLogger extends RequestBodyAdviceAdapter {
	private final ObjectMapper om;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		final String logType = defaultString(ThreadContext.get("type"), "debug");
		final String strBody = writeValueAsStringOrDefault(body, "null");

		ThreadContext.put("type", "access");
		log.info("{}", strBody.replaceAll("\"", "'"));
		ThreadContext.put("type", logType);

		return body;
	}

	private String writeValueAsStringOrDefault(Object body, String defaultValue) {
		try {
			return om.writeValueAsString(body);
		} catch (Exception ignore) {
			return defaultValue;
		}
	}
}
