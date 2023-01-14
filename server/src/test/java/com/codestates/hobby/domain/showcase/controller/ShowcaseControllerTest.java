package com.codestates.hobby.domain.showcase.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.ResultActions;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.domain.stub.ShowcaseStub;
import com.codestates.hobby.utils.ControllerTest;

@WebMvcTest({ShowcaseCommendController.class, ShowcaseQueryController.class})
class ShowcaseControllerTest extends ControllerTest {
	@MockBean
	ShowcaseService service;

	@MockBean
	ShowcaseMapper mapper;

	@Test
	void post() throws Exception {
		ShowcaseDto.Post post = ShowcaseStub.createPost();
		given(service.post(any(ShowcaseDto.Post.class))).willReturn(ShowcaseStub.createShowcase());

		ResultActions actions = defaultPostActions("/showcases", post);

		actions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").value(1L));
	}

	@Test
	void patch() throws Exception {
		ShowcaseDto.Patch patch = ShowcaseStub.createPatch();
		given(service.update(any(ShowcaseDto.Patch.class))).willReturn(ShowcaseStub.createShowcase());

		ResultActions actions = defaultPatchActions("/showcases/{showcase-id}", patch, 1L);

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(1L));
	}

	@Test
	void testForDelete() throws Exception {
		willDoNothing().given(service).delete(anyLong(), anyLong());

		ResultActions actions = mvc.perform(delete("/showcases/1"));

		actions
			.andExpect(status().isNoContent())
			.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	void testForGet() throws Exception {
		given(service.findById(anyLong())).willReturn(ShowcaseStub.createShowcase());
		given(mapper.showcaseToResponse(any(Showcase.class))).willReturn(ShowcaseStub.createResponse());
		willDoNothing().given(mapper).setProperties(any(ShowcaseDto.Response.class), anyLong());

		mvc
			.perform(get("/showcases/1"))
			.andExpect(status().isOk());
	}

	@Test
	void testForGetAll() throws Exception {
		given(service.findAll(any(PageRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());
		willDoNothing().given(mapper).setProperties(any(ShowcaseDto.Response.class), anyLong());

		ResultActions actions = defaultActionsWithPaging("/showcases");

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}

	@Test
	void getAllByCategory() throws Exception {
		given(service.findAllByCategory(anyString(), any(PageRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());
		willDoNothing().given(mapper).setProperties(any(ShowcaseDto.Response.class), anyLong());

		ResultActions actions = defaultActionsWithPaging("/categories/{category-name}/showcases", "soccer");

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}

	@Test
	void getAllByMember() throws Exception {
		given(service.findAllByMember(anyLong(), any(PageRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());
		willDoNothing().given(mapper).setProperties(any(ShowcaseDto.Response.class), anyLong());

		ResultActions actions = defaultActionsWithPaging("/members/{member-id}/showcases", 1L);

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}
}