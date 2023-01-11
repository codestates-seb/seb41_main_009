package com.codestates.hobby.domain.post.mapper;

import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post postDtoToPost(PostDto.Post requestBody);
    Post patchDtoToPost(PostDto.Patch requestBody);
    PostDto.Response postToResponseDto(Post post);
    List<PostDto.SimpleResponse> postsToSimpleResponseDtos(List<Post> posts);
}