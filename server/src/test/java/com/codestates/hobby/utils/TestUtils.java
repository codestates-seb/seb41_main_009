package com.codestates.hobby.utils;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class TestUtils {
	public static <T> MockHttpServletRequestBuilder jsonBuilder(Supplier<MockHttpServletRequestBuilder> builder) {
		return builder.get()
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8);
	}
}
