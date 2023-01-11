package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCommentMapper {
    List<PostCommentDto.Response> postCommentsToPostCommentResponseDto(List<PostComment> postComments);
}
