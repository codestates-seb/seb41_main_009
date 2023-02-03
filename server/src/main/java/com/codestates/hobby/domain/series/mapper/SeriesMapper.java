package com.codestates.hobby.domain.series.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codestates.hobby.domain.category.mapper.CategoryMapper;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, CategoryMapper.class})
public interface SeriesMapper {
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "totalPosts", expression = "java(series.getPosts().size())")
    @Mapping(target = "thumbnailUrl", expression = "java(series.getImage().getFileURL())")
    SeriesDto.Response SeriesToResponseDto(Series series);

    @Mapping(target = "totalPosts", expression = "java(series.getPosts().size())")
    @Mapping(target = "thumbnailUrl", expression = "java(series.getImage().getFileURL())")
    SeriesDto.SimpleResponse SeriesToSimpleResponseDto(Series series);
}
