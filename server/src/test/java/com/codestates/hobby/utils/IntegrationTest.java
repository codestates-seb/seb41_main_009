package com.codestates.hobby.utils;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.codestates.hobby.domain.auth.Session.SessionConst;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.showcase.repository.ShowcaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"gcs"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class IntegrationTest {
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	protected MemberRepository memberRepository;

	@Autowired
	protected FileInfoRepository fileInfoRepository;

	@Autowired
	protected ShowcaseRepository showcaseRepository;

	@Autowired
	protected ObjectMapper om;

	protected MockHttpSession session;

	protected MockMvc mvc;

	@BeforeAll
	void beforeAll() {
		mvc = MockMvcBuilders.webAppContextSetup(wac)
			.apply(springSecurity())
			.build();

		initSession();
	}

	protected void initSession() {
		session = new MockHttpSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, memberRepository.findAll().get(0));
	}

	protected ResultActions defaultPostActions(String endPoint, Object content, Object... variables) throws Exception {
		return mvc.perform(defaultActions(() -> post(endPoint, variables), content));
	}

	protected ResultActions defaultPatchActions(String endPoint, Object content, Object... variables) throws Exception {
		return mvc.perform(defaultActions(() -> patch(endPoint, variables), content));
	}

	protected ResultActions defaultPagingActions(String endPoint, int page, int size, Object... variables) throws Exception {
		return mvc.perform(
			get(endPoint, variables)
				.param("page", String.valueOf(page))
				.param("size", String.valueOf(size))
				.session(session)
		);
	}

	private <T> MockHttpServletRequestBuilder defaultActions(Supplier<MockHttpServletRequestBuilder> builder, T content) throws Exception {
		return TestUtils.jsonBuilder(builder)
			.session(session)
			.content(om.writeValueAsString(content))
			.characterEncoding(StandardCharsets.UTF_8);
	}
}
