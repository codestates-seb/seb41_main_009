package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, PostCommentMapper.class})
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

    default void setProperties(PostDto.Response response, Long memberId) {
        Optional.ofNullable(memberId)
                .ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
    }

    default void setProperties(PostDto.SimpleResponse response, Long memberId) {
        Optional.ofNullable(memberId)
                .ifPresent(id -> response.setItWriter(id.equals(response.getWriter().getId())));
    }
}