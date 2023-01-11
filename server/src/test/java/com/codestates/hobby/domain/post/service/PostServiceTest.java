package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.series.entity.Series;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostService.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PostServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper mapper;

    @Autowired
    private Gson gson;

    @BeforeAll
    static void beforeAll(){
        Member member = new Member("aaa@gmail.com",
                "홍길동",
                "Codestates11!",
                false);
        Category patentCategory = Category.createParent("운동","exercise");
        Category childCategory = Category.createChild("야구","baseball",patentCategory);
        Series series = new Series(member, "입문", childCategory, "Content");
        Post post = new Post(member,"Title",childCategory,"Content");
        Post seriesPost = new Post(member,"Title1", series,"Content", childCategory);
    }
    @Nested
    class 포스트_생성{
        @Test
        void 회원_포스트_생성(){
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
