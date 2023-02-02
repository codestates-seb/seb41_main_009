package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.mapper.SeriesMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MemberMapper.class})
public interface PostMapper {
    @Mapping(target = "writer", source = "member")
    @Mapping(target = "seriesId", source = "series")
    PostDto.Response postToResponse(Post post);

    @Mapping(target = "writer", source = "member")
    @Mapping(target = "seriesId", source = "series")
    @Mapping(target = "comments", source = "commentCount")
    @Mapping(target = "content", ignore = true)
    PostDto.SimpleResponse postToSimpleResponse(Post post);

    default String toString(Category value){
        return value.getKorName();
    }

    default Long toLong(Series value){
        return value != null ? value.getId(): 0;
    }

}