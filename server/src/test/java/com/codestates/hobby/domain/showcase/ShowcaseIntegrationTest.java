package com.codestates.hobby.domain.showcase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.codestates.hobby.domain.fileInfo.dto.FileRequestDto;
import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// @ActiveProfiles({"gcs"})
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowcaseIntegrationTest {
	private static final String END_POINT = "/showcases";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper om;

	private MockMvc mvc;

	@BeforeAll
	void beforeAll() {
		mvc = MockMvcBuilders.webAppContextSetup(wac)
			// .apply(springSecurity())
			.build();
	}

	@BeforeEach
	void beforeEach() {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(1L, ""));
	}

	@Nested
	@DisplayName("Showcase Create-Update-Delete")
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class Command {
		private List<String> urls;

		@Test
		@Order(1)
		void 쇼케이스를_작성한다() throws Exception {
			ShowcaseDto.Post post = new ShowcaseDto.Post();
			post.setContent("content");
			post.setCategory("soccer");
			post.setFileInfos(List.of(
				new FileRequestDto(5 * 1024 * 1024, 1, ImageType.PNG),
				new FileRequestDto(3 * 1024 * 1024, 2, ImageType.PNG)
			));

			ResultActions actions = mvc.perform(
				post(END_POINT)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(om.writeValueAsString(post))
			);

			MvcResult result = actions
				.andDo(print())
				.andExpect(status().isCreated())
				.andReturn();

			JsonNode root = om.readTree(result.getResponse().getContentAsString());

			urls = root.get("fileInfos")
				.findValues("fileURL").stream()
				.map(JsonNode::toString)
				.collect(Collectors.toList());
		}

		@Test
		@Order(2)
		void 이미지정보가_없으면_실패한다() throws Exception {
			ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
			patch.setContent("modified-content");
			patch.setCategory("baseball");
			patch.setFileInfos(List.of(new FileRequestDto(1, urls.get(1))));

			ResultActions actions = mvc.perform(
				patch(END_POINT + "/{showcase-id}", 1L)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(om.writeValueAsString(patch))
			);

			actions
				.andDo(print())
				.andExpect(status().isOk());
		}
	}

	@Nested
	@DisplayName("Update Showcase")
	class Patch {
		@Test
		@Order(3)
		void testForPatch() throws Exception {
			ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
			patch.setContent("modified-content");
			patch.setCategory("baseball");
			patch.setFileInfos(List.of(
				new FileRequestDto(5 * 1024 * 1024, 1, ImageType.PNG),
				new FileRequestDto(3 * 1024 * 1024, 2, ImageType.PNG)
			));

			ResultActions actions = mvc.perform(
				post(END_POINT + "/{showcase-id}", 1L)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(om.writeValueAsString(patch))
			);

			actions
				.andExpect(status().isOk());
		}
	}

	@Test
	@Order(3)
	void testForDelete() throws Exception {
		ResultActions actions =
			mvc.perform(delete(END_POINT, 1L));

		actions
			.andExpect(status().isNoContent());
	}

	@Test
	@Order(4)
	void getAll() throws Exception {
		ResultActions actions = mvc.perform(get(END_POINT).param("page", "1").param("size", "10"));

		actions
			.andExpect(status().isNoContent());
	}
}
