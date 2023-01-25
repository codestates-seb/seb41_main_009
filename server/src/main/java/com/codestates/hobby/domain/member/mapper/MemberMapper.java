package com.codestates.hobby.domain.member.mapper;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(target = "profileUrl", expression = "java(member.getImage().getFileURL())")
    MemberDto.Response MemberToResponseDto(Member member);

    @Mapping(target = "profileUrl", expression = "java(member.getImage().getFileURL())")
    MemberDto.SimpleResponse MemberToSimpleResponseDto(Member member);
}