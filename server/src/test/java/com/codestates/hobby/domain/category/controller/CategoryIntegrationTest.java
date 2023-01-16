package com.codestates.hobby.domain.category.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {
	@Autowired
	MockMvc mvc;

	@Test
	void getAll() throws Exception {
		mvc
			.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data..categories").exists())
			.andExpect(jsonPath("$.data.length()").value(6))
			.andExpect(jsonPath("sum($.data..categories.length())").value(18));
	}

	@Test
	void getGroups() throws Exception {
		mvc
			.perform(get("/categories/groups"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data..categories").doesNotExist())
			.andExpect(jsonPath("$.data.length()").value(6));
	}

	@Test
	void getCategories() throws Exception {
		mvc
			.perform(get("/categories/groups/sports"))
			.andExpect(jsonPath("$.data..categories").doesNotExist())
			.andExpect(jsonPath("$.data.length()").value(3));
	}
}
