package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.mapper.PostCommentMapper;
import com.codestates.hobby.domain.post.service.PostCommentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(PostCommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PostCommentControllerTest {
    @MockBean
    PostCommentService postCommentService;

    @MockBean
    PostCommentMapper postCommentMapper;

    @BeforeAll
    static void beforeAll(){
    }

    @Test
    void post() throws Exception {

    }

    @Test
    void patch() throws Exception {

    }

    @Test
    void delete() throws Exception {

    }

    @Test
    void getAll() throws Exception {

    }
}