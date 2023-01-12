package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(PostQueryController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PostQueryControllerTest {
    @MockBean
    PostService postService;

    @MockBean
    PostMapper postMapper;

    @BeforeAll
    static void beforeAll(){
    }
    @Test
    void get() throws Exception {

    }

    @Test
    void getAll() throws Exception {

    }

    @Test
    void getAllByCategory() throws Exception {

    }

    @Test
    void getAllByMember() throws Exception {

    }

    @Test
    void getAllBySeries() throws Exception {

    }
}