package com.codestates.hobby.domain.auth.handler;

import com.codestates.hobby.domain.auth.session.SessionConst;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Member member = (Member)authentication.getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getId());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().print(member.getId());
        response.getWriter().close();

        log.info("\n\n--로그인 성공-- Member Id : {}", member.getId());
    }
}
