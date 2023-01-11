package com.codestates.hobby.postTest;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
import com.codestates.hobby.domain.series.entity.Series;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostService.class)
@MockBean(JpaMetamodelMappingContext.class)
public class postServiceTest {
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
        Category patentCategory = Category.createParent("운동");
        Category childCategory = Category.createChild("야구",patentCategory);
        Series series = new Series(member, "입문", childCategory, "Content");
        Post post = new Post(member,"Title",childCategory,"Content");
        Post seriesPost = new Post(member,"Title1", series,"Content", childCategory);
    }
    @Test
    public void 유저_생성() throws Exception{
    }
}
