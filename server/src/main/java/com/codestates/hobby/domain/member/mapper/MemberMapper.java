package com.codestates.hobby.domain.member.mapper;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member PostDtoToMember(MemberDto.Post post);
    Member PatchDtoToMember(MemberDto.Patch patch);
    MemberDto.Response MemberToResponseDto(Member member);
    MemberDto.SimpleResponse MemberToSimpleResponseDto(Member member);
}