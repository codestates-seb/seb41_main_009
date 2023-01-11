package com.codestates.hobby.domain.post.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(PostCommentService.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PostCommentTest {
    @BeforeAll
    static void beforeAll(){
    }
    @Nested
    class 댓글_생성{
        @Test
        void 회원_댓글_생성(){
        }
        @Test
        void 비회원_댓글_생성(){
        }
        @Test
        void 내용_미포함(){
        }
    }
    @Nested
    class 댓글_수정{
        @Test
        void 회원_댓글_수정(){
        }
        @Test
        void 비회원_댓글_수정(){
        }
        @Test
        void 내용_미포함(){
        }
    }

    @Nested
    class 댓글_삭제{
        @Test
        void 회원_댓글_삭제(){
        }
        @Test
        void 비회원_댓글_삭제(){
        }
    }

    @Nested
    class 댓글_조회{
        @Test
        void 포스트를_통한_조회(){
        }
    }
}
