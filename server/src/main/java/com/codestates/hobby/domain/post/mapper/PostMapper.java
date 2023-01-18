package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, PostCommentMapper.class})
public interface PostMapper {
    @Mapping(target = "writer", source = "member")
    @Mapping(target = "seriesPosts", ignore = true)
    @Mapping(target = "seriesId", ignore = true)
    PostDto.Response postToResponse(Post post);

    @Mapping(target = "writer", source = "member")
    @Mapping(target = "comments", expression = "java(post.getComments().size())")
    @Mapping(target = "thumbnailUrl", expression = "java(post.getImages().get(0).getFileURL())")
    @Mapping(target = "seriesId", ignore = true)
    PostDto.SimpleResponse postToSimpleResponse(Post post);

    default String toString(Category value){
        return value.getKorName();
    }

    default Long toLong(Series value){
        return value.getId();
    }

    default void setProperties(PostDto.Response response, Long memberId) {
        Optional.ofNullable(memberId)
                .ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
    }

    default void setProperties(PostDto.SimpleResponse response, Long memberId) {
        Optional.ofNullable(memberId)
                .ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
    }

    default void setSeries(PostDto.Response response, Long seriesId){
        Optional.ofNullable(seriesId).ifPresent(id -> response.setSeriesId(seriesId));
    }
    default void setSeriesPostUrl(PostDto.Response response, Post post){
        if (response.getSeriesId() != null){
            List<String> seriesPostUrl = post.getSeries().getPosts().stream().map(seriesPost ->
                    "http://localhost:8080/posts/"+seriesPost.getId()).collect(Collectors.toList());
            response.setSeriesPosts(seriesPostUrl);
        }
    }
}