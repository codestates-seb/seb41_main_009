package com.codestates.hobby.domain.showcase;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.ResultActions;

import com.codestates.hobby.domain.showcase.controller.ShowcaseCommendController;
import com.codestates.hobby.domain.showcase.controller.ShowcaseQueryController;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseService;
import com.codestates.hobby.domain.stub.FileInfoStub;
import com.codestates.hobby.domain.stub.ShowcaseStub;
import com.codestates.hobby.global.config.support.InfiniteScrollRequest;
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
		given(mapper.showcaseToCommendResponse(any(Showcase.class))).willReturn(
			new ShowcaseDto.CommandResponse(1L, List.of(FileInfoStub.createResponse()))
		);

		ResultActions actions = defaultPostActions("/showcases", post);

		actions
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void patch() throws Exception {
		ShowcaseDto.Patch patch = ShowcaseStub.createPatch();
		given(service.update(any(ShowcaseDto.Patch.class))).willReturn(ShowcaseStub.createShowcase());
		given(mapper.showcaseToCommendResponse(any(Showcase.class))).willReturn(
			new ShowcaseDto.CommandResponse(1L, List.of(FileInfoStub.createResponse())));

		ResultActions actions = defaultPatchActions("/showcases/{showcase-id}", patch, 1L);

		actions
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1L));
	}

	@Test
	void testForDelete() throws Exception {
		willDoNothing().given(service).delete(anyLong(), anyLong());

		ResultActions actions = mvc.perform(
			delete("/showcases/1").session(session)
		);

		actions
			.andExpect(status().isNoContent())
			.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	void testForGet() throws Exception {
		given(service.findById(anyLong())).willReturn(ShowcaseStub.createShowcase());
		given(mapper.showcaseToResponse(any(Showcase.class))).willReturn(ShowcaseStub.createResponse());

		mvc
			.perform(get("/showcases/1"))
			.andExpect(status().isOk());
	}

	@Test
	void testForGetAll() throws Exception {
		given(service.findAll(any(InfiniteScrollRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());

		ResultActions actions = defaultActionsWithPaging("/showcases");

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}

	@Test
	void getAllByCategory() throws Exception {
		given(service.findAllByCategory(anyString(), any(InfiniteScrollRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());

		ResultActions actions = defaultActionsWithPaging("/categories/{category-name}/showcases", "soccer");

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}

	@Test
	void getAllByMember() throws Exception {
		given(service.findAllByMember(anyLong(), any(InfiniteScrollRequest.class))).willReturn(ShowcaseStub.createPage());
		given(mapper.showcaseToSimpleResponse(any(Showcase.class))).willReturn(ShowcaseStub.createSimpleResponse());

		ResultActions actions = defaultActionsWithPaging("/members/{member-id}/showcases", 1L);

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.pageInfo").exists());
	}
}