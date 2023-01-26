package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostQueryController {
    private final PostService postService;
    private final PostMapper mapper;

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<?> get(@PathVariable("post-id") long postId, @SessionAttribute Member loginMember) {
        Post post = postService.findById(postId);
        PostDto.Response response = mapper.postToResponse(post);
        mapper.setSeries(response,post);
        mapper.setSeriesPostUrl(response,post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@SessionAttribute Member loginMember,
                                    CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAll(pageRequest.to());

        return toResponseEntity(posts);
    }

    @GetMapping("/categories/{category-name}/posts")
    public ResponseEntity<?> getAllByCategory(@SessionAttribute Member loginMember,
                                              @PathVariable("category-name") String category,
                                              CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllByCategory(category, pageRequest.to());
        return toResponseEntity(posts);
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<?> getAllByMember(@SessionAttribute Member loginMember,
                                            @PathVariable("member-id") long memberId,
                                            CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllByMember(memberId, pageRequest.to());
        return toResponseEntity(posts);
    }

    @GetMapping("/series/{series-id}/posts")
    public ResponseEntity<?> getAllBySeries(@SessionAttribute Member loginMember,
                                            @PathVariable("series-id") long seriesId,
                                            CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllBySeries(seriesId, pageRequest.to());
        return toResponseEntity(posts);
    }

    private ResponseEntity<?> toResponseEntity(Page<Post> posts){
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Page<PostDto.SimpleResponse> responses = posts.map(mapper::postToSimpleResponse);
            responses.forEach(response -> mapper.setSeries(response,postService.findVerifiedPost(response.getId())));
            responses.forEach(response -> mapper.setThumbnailUrl(response,postService.findVerifiedPost(response.getId())));
            return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
        }
    }
}