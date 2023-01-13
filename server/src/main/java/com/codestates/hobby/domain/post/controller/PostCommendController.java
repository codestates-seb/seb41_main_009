package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostCommendController {
    private final PostService postService;
    private final PostMapper mapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> post(@RequestPart PostDto.Post postDto,
                                  @RequestPart MultipartFile imgFile,
                                  @AuthenticationPrincipal Long memberId) {
        postDto.setProperties(memberId, imgFile);

        Post post = postService.post(postDto);
        return new ResponseEntity<>(post.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity<?> patch(@RequestBody PostDto.Patch patchDto,
                                   @PathVariable("post-id") long postId,
                                   @RequestPart MultipartFile imgFile,
                                   @AuthenticationPrincipal Long memberId) {
        patchDto.setProperties(memberId, postId, imgFile);

        Post post = postService.update(patchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> delete(@PathVariable("post-id") long postId,
                                    @AuthenticationPrincipal Long memberId) {
        postService.delete(postId,memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
