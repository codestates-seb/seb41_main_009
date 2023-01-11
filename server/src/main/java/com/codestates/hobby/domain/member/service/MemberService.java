package com.codestates.hobby.domain.member.service;

import com.codestates.hobby.domain.member.entity.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {
    public Member create(Member member) {
        //email 중복 확인
        //verifiedEmail = repository.findByEmail(member.getEmail())

        //nickname 중복 확인
        //verifiedNickname = repository.findByNickname(member.getNickname())

        //비밀번호 암호화
        return member;
    }

    public Member edit(Long memberId, Member member) {
        //멤버가 있는지 확인
        Member findMember = findVerifiedById(memberId);
        //수정을 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }

        //변경하려는 nickname 중복 확인
        //verifiedNickname = repository.findByNickname(member.getNickname())

        return member;
    }

    public void delete(long id) {
        //멤버가 있는지 확인
        Member findMember = findVerifiedById(id);
        //삭제를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }
    }

    public Member findAll(long id) {
        //멤버가 있는지 확인
        Member findMember = findVerifiedById(id);
        //정보 조회를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }

        return null;
    }

    public Member findVerifiedById(long id) {
        //해당 id로 가입된 멤버가 있는지 확인
        //repository.findById(id)
        //있다면 해당 member를 반환하고
        //없다면 MEMBER_NOT_FOUND
        return null;
    }

    public Member findVerifiedByEmail(String email) {
        //해당 email로 가입된 멤버가 있는지 확인
        //repository.findByEmail(email)
        //있다면 해당 member를 반환하고
        //없다면 MEMBER_NOT_FOUND
        return null;
    }

    //로그인 중인 유저 가져오기
    public Member loginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser"))
            throw new RuntimeException("Unauthorized");

        //로그인 중인 멤버가 존재하는지 확인 후 존재하면 해당 member를 리턴
        //Member member = repository.findByEmail(authentication.getName())

        return null;
    }
}
