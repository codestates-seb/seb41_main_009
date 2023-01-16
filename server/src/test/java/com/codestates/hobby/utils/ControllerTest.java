package com.codestates.hobby.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@MockBean(JpaMetamodelMappingContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ControllerTest {
	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	protected ObjectMapper om;

	protected MockMvc mvc;

	@BeforeAll
	protected void beforeAll() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@BeforeEach
	void beforeEach() {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(1L, ""));
	}

	public <T> ResultActions defaultPostActions(String path, T content, Object... uriVariables) throws Exception {
		return defaultActions(() -> post(path, uriVariables), content);
	}

	public <T> ResultActions defaultPatchActions(String path, T content, Object... uriVariables) throws Exception {
		return defaultActions(() -> patch(path, uriVariables), content);
	}

	private <T> ResultActions defaultActions(Supplier<MockHttpServletRequestBuilder> builder, T content) throws Exception {
		return mvc.perform(
			builder.get()
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(content))
		);
	}

	public ResultActions defaultActionsWithPaging(String path, Object... uriVariables) throws Exception {
		return mvc.perform(
			get(path, uriVariables)
				.param("page", "1")
				.param("size", "5")
		);
	}
}
