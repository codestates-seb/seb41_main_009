package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(PostCommendController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PostCommendControllerTest {
    @MockBean
    PostService postService;

    @MockBean
    PostMapper postMapper;

    @BeforeAll
    static void beforeAll(){
    }
    @Test
    void post() {
    }

    @Test
    void patch() {
    }

    @Test
    void delete() {
    }
}
