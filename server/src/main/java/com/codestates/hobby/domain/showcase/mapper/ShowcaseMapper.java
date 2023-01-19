package com.codestates.hobby.domain.showcase.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.codestates.hobby.domain.category.mapper.CategoryMapper;
import com.codestates.hobby.domain.fileInfo.mapper.FileInfoMapper;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	uses = {MemberMapper.class, CategoryMapper.class, FileInfoMapper.class, ShowcaseCommentMapper.class})
public interface ShowcaseMapper {
	@Mapping(target = "writer", source = "member")
	@Mapping(target = "imageUrls", source = "fileInfos")
	ShowcaseDto.Response showcaseToResponse(Showcase showcase);

	@Mapping(target = "writer", source = "member")
	@Mapping(target = "lastComment", source = "comments")
	@Mapping(target = "comments", expression = "java(showcase.getComments().size())")
	@Mapping(target = "thumbnailUrl", expression = "java(showcase.getFileInfos().get(0).getFileURL())")
	ShowcaseDto.SimpleResponse showcaseToSimpleResponse(Showcase showcase);

	ShowcaseDto.CommandResponse showcaseToCommendResponse(Showcase showcase);

	default void setProperties(ShowcaseDto.Response response, Long memberId) {
		Optional.ofNullable(memberId)
			.ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
	}

	default void setProperties(ShowcaseDto.SimpleResponse response, Long memberId) {
		Optional.ofNullable(memberId)
			.ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
	}
}
