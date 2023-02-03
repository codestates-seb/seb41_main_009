package com.codestates.hobby.domain.post.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostQueryController {
    private final PostService postService;
    private final PostMapper mapper;

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<?> get(@PathVariable("post-id") long postId) {
        Post post = postService.findById(postId);
        PostDto.Response response = mapper.postToResponse(post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAll(CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAll(pageRequest.to());

        return toResponseEntity(posts);
    }

    @GetMapping("/categories/{category-name}/posts")
    public ResponseEntity<?> getAllByCategory(@PathVariable("category-name") String category,
                                              CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllByCategory(category, pageRequest.to());
        return toResponseEntity(posts);
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId,
                                            CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllByMember(memberId, pageRequest.to());
        return toResponseEntity(posts);
    }

    @GetMapping("/series/{series-id}/posts")
    public ResponseEntity<?> getAllBySeries(@PathVariable("series-id") long seriesId,
                                            CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findAllBySeries(seriesId, pageRequest.to());
        return toResponseEntity(posts);
    }

    @GetMapping("/posts/search")
    public ResponseEntity<?> search(@RequestParam String query, CustomPageRequest pageRequest) {
        Page<Post> posts = postService.findByContent(query, pageRequest.to());

        return toResponseEntity(posts);
    }

    private ResponseEntity<?> toResponseEntity(Page<Post> posts){
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Page<PostDto.SimpleResponse> responses = posts.map(mapper::postToSimpleResponse);
            return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
        }
    }
}
