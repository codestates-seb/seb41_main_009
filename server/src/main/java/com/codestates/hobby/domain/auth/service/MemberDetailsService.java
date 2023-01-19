package com.codestates.hobby.domain.auth.service;

import com.codestates.hobby.domain.auth.utils.CustomAuthorityUtils;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException("not found member"));
        return new MemberDetails(findMember);
    }

    public class MemberDetails extends Member implements UserDetails {
        public MemberDetails(Member member) {
            setUserInfo(member.getId(), member.getEmail(),
                    member.getNickname(), member.getPassword(), member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        //이메일 인증 여부 확인
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            if (getMemberStatus().equals(MemberStatus.MEMBER_QUIT)) return false;
            return true;
        }
    }
}
