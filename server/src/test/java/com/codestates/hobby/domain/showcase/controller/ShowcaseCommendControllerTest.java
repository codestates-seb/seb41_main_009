package com.codestates.hobby.domain.showcase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.utils.ControllerTest;

@WebMvcTest(value = ShowcaseCommendController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class ShowcaseCommendControllerTest extends ControllerTest {
	@MockBean
	ShowcaseService service;

	@MockBean
	ShowcaseMapper mapper;

	@Test
	void post() {
	}

	@Test
	void patch() {
	}

	@Test
	void delete() {
	}
}