package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.repository.PostRepository;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.service.SeriesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    static PostRepository postRepository;


    @Mock
    static MemberService memberService;

    @Autowired
    static PostMapper postMapper;

    @Mock
    static CategoryService categoryService;

    @Mock
    static SeriesService seriesService;

    private static List<String> urls =
        Arrays.asList("http://domain.com/bucket/basepath/file1.png","http://domain.com/bucket/basepath/file2.png","http://domain.com/bucket/basepath/file3.png");

    @Nested
    class 포스트_생성{
        @Test
        void 회원_포스트_생성(){
            Member member = new Member("aaa@gmail.com",
                "홍길동",
                "Codestates11!","introduction",
                false, "http://domain.com/bucket/basepath/file.png");
            Category patentCategory = Category.createParent("운동","exercise");
            Category childCategory = Category.createChild("야구","baseball",patentCategory);
            Series series = new Series(member, childCategory, "입문", "Content", urls.get(0));
            Post post = new Post(member,"Title",series,childCategory,"Content",urls);


            PostDto.Post postDto = new PostDto.Post();

            postDto.setTitle("Title");
            postDto.setContent("Content");
            postDto.setMemberId(1L);
            postDto.setSeriesId(1L);
            postDto.setCategory("야구");
            postDto.setImgUrls(urls);

            given(postRepository.save(any())).willReturn(post);

            Post findPost = postService.post(postDto);

            Assertions.assertEquals(findPost.getTitle(), postDto.getTitle());
        }
        @Test
        void 비회원_포스트_생성(){
        }
        @Test
        void 제목_내용_카테고리_미포함(){
        }
    }
    @Nested
    class 포스트_수정{
        @Test
        void 회원_포스트_수정(){
            Member member = new Member("aaa@gmail.com",
                    "홍길동",
                    "Codestates11!","introduction",
                    false, "http://domain.com/bucket/basepath/file.png");

            Category patentCategory = Category.createParent("운동","exercise");
            Category childCategory = Category.createChild("야구","baseball",patentCategory);
            Series series = new Series(member, childCategory, "입문", "입문", urls.get(0));
            Post post = new Post(member,"Title",series,childCategory,"Content",urls);

            PostDto.Patch patchDto = new PostDto.Patch();

            patchDto.setTitle("Title_change");
            patchDto.setContent("Content_change");
            patchDto.setPostId(1L);
            patchDto.setSeriesId(1L);
            patchDto.setMemberId(1L);
            patchDto.setCategory("야구");
            patchDto.setImgUrls(urls);

            given(postRepository.save(any())).willReturn(post);
            given(postRepository.findById(anyLong())).willReturn(Optional.of(post));
            given(memberService.findMemberById((anyLong()))).willReturn(member);

            Post findPost = postService.update(patchDto);

            Assertions.assertEquals(findPost.getTitle(), patchDto.getTitle());
        }
        @Test
        void 비회원_포스트_수정(){
        }
        @Test
        void 제목_내용_카테고리_미포함(){
        }
    }

    @Nested
    class 포스트_삭제{
        @Test
        void 회원_포스트_삭제(){
        }
        @Test
        void 비회원_포스트_삭제(){
        }
    }

    @Nested
    class 포스트_조회{
        @Test
        void 검색어를_통한_조회(){
        }
        @Test
        void 유저를_통한_조회(){
        }
        @Test
        void 카테고리를_통한_조회(){
        }
        @Test
        void 시리즈를_통한_조회(){
        }
    }
}
