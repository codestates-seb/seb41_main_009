package com.codestates.hobby.domain.member.mapper;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    @Mapping(target = "profileUrl", source = "image.fileURL", defaultValue = "https://cdn-icons-png.flaticon.com/512/1946/1946429.png")
    MemberDto.Response MemberToResponseDto(Member member);

    @Mapping(target = "profileUrl", source = "image.fileURL", defaultValue = "https://cdn-icons-png.flaticon.com/512/1946/1946429.png")
    MemberDto.SimpleResponse MemberToSimpleResponseDto(Member member);
}