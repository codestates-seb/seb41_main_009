package com.codestates.hobby.domain.category.controller;

import static com.codestates.hobby.domain.stub.CategoryStub.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import com.codestates.hobby.domain.category.dto.CategoryDto;
import com.codestates.hobby.domain.category.mapper.CategoryMapper;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.utils.ControllerTest;

@WebMvcTest(value = CategoryController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class CategoryControllerTest extends ControllerTest {
	@MockBean
	CategoryService service;

	@MockBean
	CategoryMapper mapper;

	List<CategoryDto.Response> response;

	@BeforeAll
	void beforeAll() {
		response = List.of(groupResponse());
	}

	@Test
	void getGroups() throws Exception {
		// given
		given(service.findAllGroups()).willReturn(List.of());
		given(mapper.categoriesToResponses(anyList())).willReturn(response);

		// when
		ResultActions actions = mvc.perform(get("/categories/groups"));

		// then
		actions
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void getCategories() throws Exception {
		// given

		// when

		// then
	}

	@Test
	void getAll() throws Exception {
		// given

		// when

		// then
	}
}