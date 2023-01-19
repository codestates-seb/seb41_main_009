package com.codestates.hobby.domain.auth.service;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member login(String email, String password) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_MEMBER));

        //matches(CharSequence rawPassword, String encodedPassword);
        if(passwordEncoder.matches(password, member.getPassword())) return member;
        else return null;
    }
}
