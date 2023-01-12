package com.codestates.hobby.domain.member;

import com.codestates.hobby.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    //@Mock
    //private MemberRepository repository;

    @InjectMocks
    private MemberService service;

    @Nested
    @DisplayName("회원 가입")
    class create {
        void 이메일_중복X() {
        }
        void 닉네임_중복X() {
        }
        void 이메일로_인증번호를_발송한다() {
        }
        void 인증번호가_일치해야한다() {
        }
    }

    @Nested
    @DisplayName("회원 정보 수정")
    class edit {
        void 닉네임_중복X() {
        }
        void 본인의_정보만_수정_가능() {
        }
    }

    @Nested
    @DisplayName("회원 탈퇴")
    class delete {
        void 회원_상태를_탈퇴로_변경() {
        }
    }

    @Nested
    @DisplayName("회원 정보 조회")
    class find {
        void 본인의_정보만_조회_가능() {
        }
    }
}
