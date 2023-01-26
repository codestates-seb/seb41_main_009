package com.codestates.hobby.domain.showcase;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;

import com.codestates.hobby.domain.showcase.controller.ShowcaseCommentController;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;
import com.codestates.hobby.domain.showcase.mapper.ShowcaseCommentMapper;
import com.codestates.hobby.domain.showcase.service.ShowcaseCommentService;
import com.codestates.hobby.domain.stub.ShowcaseCommentStub;
import com.codestates.hobby.utils.ControllerTest;

@WebMvcTest(value = ShowcaseCommentController.class)
class ShowcaseCommentControllerTest extends ControllerTest {
	@MockBean
	ShowcaseCommentService service;

	@MockBean
	ShowcaseCommentMapper mapper;

	@Test
	void testForGetAll() throws Exception {
		willDoNothing().given(mapper).setProperties(any(), anyLong());
		given(mapper.entityToResponse(any())).willReturn(ShowcaseCommentStub.createResponse());
		given(service.findAll(anyLong(), any(PageRequest.class)))
			.willReturn(new PageImpl<>(List.of(new ShowcaseComment("content", null, null))));

		ResultActions actions = defaultActionsWithPaging("/showcases/1/comments");

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.length()").value(1));
	}

	@Test
	void testForPost() throws Exception {
		ShowcaseDto.Post post = new ShowcaseDto.Post();
		ShowcaseComment comment = new ShowcaseComment("content", null, null);
		ReflectionTestUtils.setField(comment, "id", 1L);

		given(service.comment(any())).willReturn(comment);

		ResultActions actions = defaultPostActions("/showcases/1/comments", post);

		actions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").value(1L));
	}

	@Test
	void testForPatch() throws Exception {
		Long commentId = 5L;
		ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
		ShowcaseComment comment = new ShowcaseComment("content", null, null);
		ReflectionTestUtils.setField(comment, "id", commentId);

		given(service.comment(any())).willReturn(comment);

		ResultActions actions = defaultPatchActions("/showcases/1/comments/{comment-id}", patch, commentId);

		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(commentId));
	}

	@Test
	void testForDelete() throws Exception {
		willDoNothing().given(service).delete(anyLong(), anyLong(), anyLong());

		mvc
			.perform(delete("/showcases/1/comments/1"))
			.andExpect(status().isNoContent());
	}
}
