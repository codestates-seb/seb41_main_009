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
		CategoryDto.Response response = groupResponse();
		response.setCategories(null);
		givens(List.of(response));

		mvc
			.perform(get("/categories/groups"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data..categories").doesNotExist());
	}

	@Test
	void getCategories() throws Exception {
		givens(response.get(0).getCategories());

		mvc
			.perform(get("/categories/groups/exercise"))
			.andDo(print())
			.andExpect(jsonPath("$.data..categories").doesNotExist());
	}

	@Test
	void getAll() throws Exception {
		givens(response);

		mvc
			.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data..categories").exists());
	}

	private void givens(List<CategoryDto.Response> categories) {
		given(service.findAllGroups()).willReturn(List.of());
		given(mapper.categoriesToResponses(anyList())).willReturn(categories);
	}
}