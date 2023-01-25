package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.PostComment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",  uses = {MemberMapper.class})
public interface PostCommentMapper {
    @Mapping(target = "writer", source = "member")
    PostCommentDto.Response postCommentToPostCommentResponse(PostComment comment);
}
