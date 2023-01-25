package com.codestates.hobby.domain.post.tempTest;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.repository.PostRepository;
import com.codestates.hobby.domain.post.service.PostService;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.repository.SeriesRepository;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AsyncTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SeriesRepository seriesRepository;


    @BeforeEach
    void setUp(){
        List<String> urls = List.of("http://domain.com/bucket/possdftsa/filsdfeasd.png");
        Member member = new Member("aaa@gmail.com",
                "홍길동",
                "Codestates11!", "introduction",
                false, "http://domain.com/buckekkkt/basekkkpath/file.png", List.of("TestUser"));
        Category category = categoryRepository.findById(1L).get();
        Series series = new Series(member, category, "입문", "Content", urls.get(0));
        memberRepository.save(member);
        seriesRepository.save(series);
    }


    @Test
    public void 포스트_생성(){
        PostDto.Post post = new PostDto.Post();
        post.setMemberId(1L);
        post.setTitle("title");
        post.setContent("content");
        post.setCategory("movie");
        post.setSeriesId(1L);
        postService.post(post);
    }
}
