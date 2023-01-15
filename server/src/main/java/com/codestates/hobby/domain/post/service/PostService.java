package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.fileInfo.service.FileInfoService;
import com.codestates.hobby.domain.post.repository.PostRepository;
import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final SeriesService seriesService;
    private final FileInfoService fileInfoService;

    @Transactional
    public Post post(PostDto.Post postDto){
        Member member = memberService.findMemberById(postDto.getMemberId());
        Category category = categoryService.findHobbyByName(postDto.getCategory());
        if (postDto.getSeriesId() != null){
            Series series = seriesService.findById(postDto.getSeriesId());
            Post post = new Post(member,postDto.getTitle(),series,category,postDto.getContent(),postDto.getImgUrls());
            return postRepository.save(post);
        }
        else {
            Post post = new Post(member,postDto.getTitle(),category,postDto.getContent(),postDto.getImgUrls());
            return postRepository.save(post);
        }
    }

    @Transactional
    public Post update(PostDto.Patch patchDto){
        Post findPost = findVerifiedPost(patchDto.getPostId());
        // mock 테스트를 위한 임시 수정.
        if (!isMatchMember(findPost, memberService.findMemberById(patchDto.getMemberId()).getId())) throw new RuntimeException("권한이 없습니다.");
        else {
            findPost.updatePost(patchDto.getTitle(),patchDto.getContent(),categoryService.findHobbyByName(patchDto.getCategory()),
                    seriesService.findById(patchDto.getSeriesId()),patchDto.getImgUrls());
            return postRepository.save(findPost);
        }
    }
    @Transactional
    public void delete(long postId, long memberId){
        Post findPost = findVerifiedPost(postId);
        if (!isMatchMember(findPost, memberService.findMemberById(memberId).getId())) throw new RuntimeException("권한이 없습니다.");
        else postRepository.delete(findPost);
    }

    @Transactional
    public Post findById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Cannot find post"));
    }

    @Transactional(readOnly = true)
    public Page<Post> findByContent(String content, PageRequest pageRequest) {
        return postRepository.findAllByContentContainsOrderByIdDesc(content,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest.withSort(Sort.by("id").descending()));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByMember(long memberId, PageRequest pageRequest) {
        return postRepository.findAllByMemberIdOrderByIdDesc(memberId,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByCategory(String categoryName, PageRequest pageRequest) {
        return postRepository.findAllByCategoryOrderByIdDesc(categoryService.findHobbyByName(categoryName),pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllBySeries(long seriesId, PageRequest pageRequest) {
        return postRepository.findAllBySeriesIdOrderByIdDesc(seriesId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Post findVerifiedPost(long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow(() -> new RuntimeException("data is null"));
        return findPost;
    }

    @Transactional(readOnly = true)
    public boolean isMatchMember(Post post, long memberId){
        return post.getMember().getId().equals(memberId);
    }

}
