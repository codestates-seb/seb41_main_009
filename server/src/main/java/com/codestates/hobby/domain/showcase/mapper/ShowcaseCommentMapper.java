package com.codestates.hobby.domain.showcase.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.showcase.dto.ShowcaseCommentDto;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, ShowcaseCommentMapper.class})
public interface ShowcaseCommentMapper {
	@Mapping(target = "writer", source = "member")
	ShowcaseCommentDto.Response entityToResponse(ShowcaseComment comment);

	default void setProperties(ShowcaseCommentDto.Response response, Long memberId) {
		Optional.ofNullable(memberId)
			.ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
	}
}
