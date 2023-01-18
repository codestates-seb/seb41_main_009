package com.codestates.hobby.domain.post.controller;


import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.post.mapper.PostCommentMapper;
import com.codestates.hobby.domain.post.service.PostCommentService;
import com.codestates.hobby.domain.series.entity.Series;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostCommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JpaMetamodelMappingContext.class)
public class PostCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PostCommentService postCommentService;

    @MockBean
    PostCommentMapper postCommentMapper;

    @Autowired
    Gson gson;

    private static FileInfo fileInfo;
    private static Member member;
    private static Category patentCategory;
    private static Category childCategory;
    private static Series series;
    private static Post post;
    private static List<String> urls =
        Arrays.asList("http://domain.com/bucket/basepath/file1.png","http://domain.com/bucket/basepath/file2.png","http://domain.com/bucket/basepath/file3.png");

    @BeforeAll
    public static void init(){

        member = new Member("aaa@gmail.com",
                "홍길동",
                "Codestates11!","introduction",
                false, "http://domain.com/bucket/basepath/file.png");
        fileInfo = member.getImage();
        patentCategory = Category.createParent("운동","exercise");
        childCategory = Category.createChild("야구","baseball",patentCategory);
        series = new Series(member,  childCategory, "Title","Content","http://domain.com/bucket/basepath/file.png");
        post = new Post(member,"Title",series,childCategory,"Content",urls);
    }

    @Test
    void create() throws Exception{
        PostCommentDto.Post postDto = new PostCommentDto.Post();
        postDto.setContent("Comment");
        postDto.setProperties(1L,1L);
        PostComment postComment = new PostComment("Comment", member, post);

        given(postCommentService.post(Mockito.any(PostCommentDto.Post.class))).willReturn(postComment);

        String content = gson.toJson(postDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/posts/{post-id}/comments",1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value(postDto.getContent()));
    }

    @Test
    void update() throws Exception{
        PostCommentDto.Patch patchDto = new PostCommentDto.Patch();
        patchDto.setContent("Comment");
        patchDto.setProperties(1L,1L,1L);
        PostComment postComment = new PostComment("Comment", member, post);
        
        given(postCommentService.update(Mockito.any(PostCommentDto.Patch.class))).willReturn(postComment);

        String content = gson.toJson(patchDto);

        ResultActions actions =
                mockMvc.perform(
                        patch("/posts/1/comments/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(patchDto.getContent()));
    }

    @Test
    void remove() throws Exception {

        doNothing().when(postCommentService).delete(anyLong(),anyLong(),anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/posts/1/comments/1")
        );

        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void findAll() throws Exception{
        PostCommentDto.Response response = new PostCommentDto.Response();

        response.setId(1L);
        response.setContent("Content");
        response.setWriter(null);
        response.setItWriter(true);
        response.setCreatedAt(LocalDateTime.now());
        response.setLastModifiedAt(LocalDateTime.now());
        PostComment postComment = new PostComment("Comment", member, post);

        //Comment를 Page로 주는게 맞나??..
        Page<PostComment> pageComment = new PageImpl<>(
                List.of(postComment),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 1);

        given(postCommentService.findAll(anyLong(),any(PageRequest.class))).willReturn(pageComment);
        given(postCommentMapper.postCommentToPostCommentResponse(any(PostComment.class))).willReturn(response);
        willDoNothing().given(postCommentMapper).setProperties(any(PostCommentDto.Response.class), anyLong());

        ResultActions actions = mockMvc.perform(
                get("/posts/{post-id}/comments",1)
                        .param("page", "1")
                        .param("size", "10")
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}
