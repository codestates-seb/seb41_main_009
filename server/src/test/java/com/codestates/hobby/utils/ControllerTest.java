package com.codestates.hobby.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.codestates.hobby.domain.auth.Session.SessionConst;
import com.codestates.hobby.domain.member.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;

@MockBean(JpaMetamodelMappingContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ControllerTest {
	@Autowired
	private WebApplicationContext ctx;

	@Autowired
	protected ObjectMapper om;

	protected MockMvc mvc;
	protected MockHttpSession session;

	@BeforeAll
	protected void beforeAll() throws Exception {
		Constructor<Member> constructor = Member.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		Member member = constructor.newInstance();
		ReflectionTestUtils.setField(member, "id", 1L);

		session = new MockHttpSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, member);

		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	public <T> ResultActions defaultPostActions(String path, T content, Object... uriVariables) throws Exception {
		return defaultActions(() -> post(path, uriVariables), content);
	}

	public <T> ResultActions defaultPatchActions(String path, T content, Object... uriVariables) throws Exception {
		return defaultActions(() -> patch(path, uriVariables), content);
	}

	private <T> ResultActions defaultActions(Supplier<MockHttpServletRequestBuilder> builder, T content) throws
		Exception {
		return mvc.perform(
			TestUtils.jsonBuilder(builder)
				.content(om.writeValueAsString(content))
				.session(session)
		);
	}

	public ResultActions defaultActionsWithPaging(String path, Object... uriVariables) throws Exception {
		return mvc.perform(
			get(path, uriVariables)
				.param("offset", "1")
				.param("page", "1")
				.param("size", "5")
		);
	}
}
