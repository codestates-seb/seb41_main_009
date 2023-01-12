package com.codestates.hobby.domain.showcase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codestates.hobby.domain.showcase.mapper.ShowcaseCommentMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseCommentService;
import com.codestates.hobby.utils.ControllerTest;

@WebMvcTest(value = ShowcaseCommentController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class ShowcaseCommentControllerTest extends ControllerTest {
	@MockBean
	ShowcaseCommentService service;

	@MockBean
	ShowcaseCommentMapper mapper;

	@Test
	void getAll() {
	}

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

