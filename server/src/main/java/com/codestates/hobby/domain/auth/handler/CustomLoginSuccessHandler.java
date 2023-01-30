package com.codestates.hobby.domain.auth.handler;

//import com.codestates.hobby.domain.auth.repository.MemberRedisRepository;
import com.codestates.hobby.domain.auth.session.SessionConst;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    //private final MemberRepository memberRepository;
    //private final HttpSession httpSession;
    //private final MemberRedisRepository redisRepository;
    //private final RedisTemplate<Long, Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Member member = (Member)authentication.getPrincipal();
        HttpSession session = request.getSession(false);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member.getId());
        //redisTemplate.opsForValue().set(member.getId(), request.getSession());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.getWriter().print(member.getId());
        response.getWriter().close();
        log.info("\n\n--로그인 성공-- Member Id : {}", member.getId());
    }
}
