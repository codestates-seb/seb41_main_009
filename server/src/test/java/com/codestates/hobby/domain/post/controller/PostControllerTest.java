package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.mapper.PostMapper;
import com.codestates.hobby.domain.post.service.PostService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({PostCommendController.class, PostQueryController.class})
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JpaMetamodelMappingContext.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PostService postService;

    @MockBean
    PostMapper postMapper;

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
            false, "http://domain.com/bucket/basepath/file.png",List.of("role"));
        fileInfo = member.getImage();
        patentCategory = Category.createParent("운동","exercise");
        childCategory = Category.createChild("야구","baseball",patentCategory);
        series = new Series(member, childCategory, "입문", "Content", urls.get(0));
        post = new Post(member,"Title",series,childCategory,"Content",urls);
    }

    @Test
    void create() throws Exception{
        PostDto.Post postDto = new PostDto.Post();
        postDto.setTitle("Title");
        postDto.setContent("Content");
        postDto.setMemberId(1L);
        postDto.setSeriesId(1L);
        postDto.setCategory("야구");
        postDto.setImgUrls(urls);

        given(postService.post(Mockito.any(PostDto.Post.class))).willReturn(post);

        String content = gson.toJson(postDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void update() throws Exception {
        PostDto.Patch patchDto = new PostDto.Patch();
        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));
        patchDto.setTitle("Title");
        patchDto.setContent("Content");
        patchDto.setMemberId(1L);
        patchDto.setPostId(1L);
        patchDto.setSeriesId(1L);
        patchDto.setCategory("야구");
        patchDto.setImgUrls(urls);

        given(postService.update(Mockito.any(PostDto.Patch.class))).willReturn(post);

        String content = gson.toJson(patchDto);

        ResultActions actions =
                mockMvc.perform(
                        patch("/posts/{post-id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void remove() throws Exception {

        doNothing().when(postService).delete(anyLong(),anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/posts/{post-id}", 1)
        );

        actions
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void find() throws Exception{
        PostCommentDto.Response commentDto = new PostCommentDto.Response();
        commentDto.setId(1L);
        commentDto.setWriter(null);
        commentDto.setContent("comment");
        commentDto.setCreatedAt(LocalDateTime.now());
        commentDto.setModifiedAt(LocalDateTime.now());

        PostDto.Response response = new PostDto.Response();

        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

        response.setId(1L);
        response.setTitle("Title");
        response.setContent("Content");
        response.setViews(10);
        response.setCreatedAt(LocalDateTime.now());
        response.setModifiedAt(LocalDateTime.now());
        response.setCategory("야구");
        response.setSeriesId(1L);
        response.setComments(List.of(commentDto));

        given(postService.findById(anyLong())).willReturn(post);
        given(postMapper.postToResponse(any(Post.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(
                get("/posts/{post-id}", 1)
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }
    @Test
    void findAll() throws Exception{
        PostDto.SimpleResponse simpleResponse = new PostDto.SimpleResponse();

        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

        simpleResponse.setId(1L);
        simpleResponse.setTitle("Title");
        simpleResponse.setContent("Content");
        simpleResponse.setViews(10);
        simpleResponse.setCreatedAt(LocalDateTime.now());
        simpleResponse.setModifiedAt(LocalDateTime.now());
        simpleResponse.setCategory("야구");
        simpleResponse.setSeriesId(1L);
        simpleResponse.setComments(10);
        simpleResponse.setWriter(null);
        simpleResponse.setThumbnailUrl("thumbUrl");

        Page<Post> pagePost = new PageImpl<>(
                List.of(post),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 1);

        given(postService.findAll(any(PageRequest.class))).willReturn(pagePost);
        given(postMapper.postToSimpleResponse(any(Post.class))).willReturn(simpleResponse);

        ResultActions actions = mockMvc.perform(
                get("/posts")
                        .param("page", "1")
                        .param("size", "10")
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void findAllByCategory() throws Exception{
        PostDto.SimpleResponse simpleResponse = new PostDto.SimpleResponse();

        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

        simpleResponse.setId(1L);
        simpleResponse.setTitle("Title");
        simpleResponse.setContent("Content");
        simpleResponse.setViews(10);
        simpleResponse.setCreatedAt(LocalDateTime.now());
        simpleResponse.setModifiedAt(LocalDateTime.now());
        simpleResponse.setCategory("야구");
        simpleResponse.setSeriesId(1L);
        simpleResponse.setComments(10);
        simpleResponse.setWriter(null);
        simpleResponse.setThumbnailUrl("thumbUrl");

        Page<Post> pagePost = new PageImpl<>(
                List.of(post),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 1);

        given(postService.findAllByCategory(anyString(),any(PageRequest.class))).willReturn(pagePost);
        given(postMapper.postToSimpleResponse(any(Post.class))).willReturn(simpleResponse);

        ResultActions actions = mockMvc.perform(
                get("/categories/{category-name}/posts", 1)
                        .param("page", "1")
                        .param("size", "10")
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
    @Test
    void findAllBySeries() throws Exception{
        PostDto.SimpleResponse simpleResponse = new PostDto.SimpleResponse();

        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

        simpleResponse.setId(1L);
        simpleResponse.setTitle("Title");
        simpleResponse.setContent("Content");
        simpleResponse.setViews(10);
        simpleResponse.setCreatedAt(LocalDateTime.now());
        simpleResponse.setModifiedAt(LocalDateTime.now());
        simpleResponse.setCategory("야구");
        simpleResponse.setSeriesId(1L);
        simpleResponse.setComments(10);
        simpleResponse.setWriter(null);
        simpleResponse.setThumbnailUrl("thumbUrl");

        Page<Post> pagePost = new PageImpl<>(
                List.of(post),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 1);

        given(postService.findAllBySeries(anyLong(),any(PageRequest.class))).willReturn(pagePost);
        given(postMapper.postToSimpleResponse(any(Post.class))).willReturn(simpleResponse);

        ResultActions actions = mockMvc.perform(
                get("/series/{series-id}/posts", 1)
                        .param("page", "1")
                        .param("size", "10")
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
    @Test
    void findAllByMember() throws Exception{
        PostDto.SimpleResponse simpleResponse = new PostDto.SimpleResponse();

        List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

        simpleResponse.setId(1L);
        simpleResponse.setTitle("Title");
        simpleResponse.setContent("Content");
        simpleResponse.setViews(10);
        simpleResponse.setCreatedAt(LocalDateTime.now());
        simpleResponse.setModifiedAt(LocalDateTime.now());
        simpleResponse.setCategory("야구");
        simpleResponse.setSeriesId(1L);
        simpleResponse.setComments(10);
        simpleResponse.setWriter(null);
        simpleResponse.setThumbnailUrl("thumbUrl");

        Page<Post> pagePost = new PageImpl<>(
                List.of(post),
                PageRequest.of(0, 10, Sort.by("postId").descending()), 1);

        given(postService.findAllByMember(anyLong(),any(PageRequest.class))).willReturn(pagePost);
        given(postMapper.postToSimpleResponse(any(Post.class))).willReturn(simpleResponse);

        ResultActions actions = mockMvc.perform(
                get("/members/{member-id}/posts", 1)
                        .param("page", "1")
                        .param("size", "10")
        );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}
