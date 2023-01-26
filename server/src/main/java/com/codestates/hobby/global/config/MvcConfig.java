package com.codestates.hobby.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codestates.hobby.domain.fileInfo.support.BasePathConverter;
import com.codestates.hobby.global.config.support.InfiniteScrollArgumentResolver;
import com.codestates.hobby.global.config.support.PagingArgumentResolver;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder()
			.serializationInclusion(JsonInclude.Include.NON_NULL)
			.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
			.visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
			.visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
			.visibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new PagingArgumentResolver());
		resolvers.add(new InfiniteScrollArgumentResolver());
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new BasePathConverter());
	}
}
