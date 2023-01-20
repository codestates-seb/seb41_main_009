package com.codestates.hobby.domain.auth.controller;

import com.codestates.hobby.domain.auth.Session.SessionConst;
import com.codestates.hobby.domain.auth.Session.SessionManager;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session == null){
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        //세션에 회원 데이터가 없으면 home

        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}
