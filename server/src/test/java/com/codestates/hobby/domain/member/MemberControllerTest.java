package com.codestates.hobby.domain.member;

import com.codestates.hobby.domain.member.controller.MemberController;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.utils.ControllerTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(value = MemberController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MemberControllerTest extends ControllerTest {
    @MockBean
    MemberService service;

    @MockBean
    MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void post() throws Exception {
        //given : 테스트용 request body 생성
        //when : MockMvc 객체로 테스트 대상 Controller 호출
        //then : Controller 핸들러 메서드에서 응답으로 수신한 HTTP Status 및 response body 검증
    }

    @Test
    public void patch() {
        //given
        //when
        //then
    }

    @Test
    public void delete() {
        //given
        //when
        //then
    }

    @Test
    public void getAll() {
        //given
        //when
        //then
    }
}
