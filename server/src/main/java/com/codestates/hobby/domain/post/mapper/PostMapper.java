package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MemberMapper.class, PostCommentMapper.class})
public interface PostMapper {
    @Mapping(target = "writer", source = "member")
    PostDto.Response postToResponse(Post post);

    @Mapping(target = "writer", source = "member")
    @Mapping(target = "comments", expression = "java(post.getComments().size())")
    PostDto.SimpleResponse postToSimpleResponse(Post post);

    default String toString(Category value){
        return value.getKorName();
    }

    default Long toLong(Series value){
        return value.getId();
    }

    default void setSeries(PostDto.Response response, Post post){
        Optional.ofNullable(post.getSeries()).ifPresent(id -> response.setSeriesId(post.getSeries().getId()));
    }

    default void setSeries(PostDto.SimpleResponse response, Post post){
        Optional.ofNullable(post.getSeries()).ifPresent(id -> response.setSeriesId(post.getSeries().getId()));
    }

    default void setSeriesPostUrl(PostDto.Response response, Post post){
        if (post.getSeries() != null){
            List<String> seriesPostUrl = post.getSeries().getPosts().stream().map(seriesPost ->
                    "http://localhost:8080/posts/"+seriesPost.getId()).collect(Collectors.toList());
            response.setSeriesPosts(seriesPostUrl);
        }
    }

    default void setThumbnailUrl(PostDto.SimpleResponse response, Post post){
        if (post.getImages().size() != 0) response.setThumbnailUrl(post.getImages().get(0).getFileURL());
        else response.setThumbnailUrl("default image url");
    }
}