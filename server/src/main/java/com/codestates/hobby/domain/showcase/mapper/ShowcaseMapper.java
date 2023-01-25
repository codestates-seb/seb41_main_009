package com.codestates.hobby.domain.showcase.mapper;

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
	@Mapping(target = "imageUrls", source = "fileInfos", qualifiedByName = "Query")
	ShowcaseDto.Response showcaseToResponse(Showcase showcase);

	@Mapping(target = "writer", source = "member")
	@Mapping(target = "comments", expression = "java(showcase.getComments().size())")
	@Mapping(target = "thumbnailUrl", expression = "java(showcase.getFileInfos().get(0).getFileURL())")
	ShowcaseDto.SimpleResponse showcaseToSimpleResponse(Showcase showcase);

	@Mapping(target = "fileInfos", qualifiedByName = "Command")
	ShowcaseDto.CommandResponse showcaseToCommendResponse(Showcase showcase);
}
