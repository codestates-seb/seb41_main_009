package com.codestates.hobby.domain.showcase.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, ShowcaseCommentMapper.class})
public interface ShowcaseMapper {
	@Mapping(target = "writer", source = "member")
	ShowcaseDto.Response showcaseToResponse(Showcase showcase);

	@Mapping(target = "writer", source = "member")
	@Mapping(target = "comments", expression = "java(showcase.getComments().size())")
	ShowcaseDto.SimpleResponse showcaseToSimpleResponse(Showcase showcase);

	// TODO(File): ImageUrls

	default String toString(Category value) {
		return value.getKorName();
	}

	default void setProperties(ShowcaseDto.Response response, Long memberId) {
		Optional.ofNullable(memberId)
			.ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
	}

	default void setProperties(ShowcaseDto.SimpleResponse response, Long memberId) {
		Optional.ofNullable(memberId)
			.ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
	}
}
