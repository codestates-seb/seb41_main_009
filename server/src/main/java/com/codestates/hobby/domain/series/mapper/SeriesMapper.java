package com.codestates.hobby.domain.series.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MemberMapper.class})
public interface SeriesMapper {
    @Mapping(target = "totalPosts", expression = "java(series.getPosts().size())")
    @Mapping(target = "postId", expression = "java(series.getPosts().get(0).getId())")
    SeriesDto.Response SeriesToResponseDto(Series series);

    SeriesDto.SimpleResponse SeriesToSimpleResponseDto(Series series);

    default String toString(Category value) {
        return value.getKorName();
    }
}
