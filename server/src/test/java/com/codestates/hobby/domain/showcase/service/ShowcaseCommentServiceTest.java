package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.post.repository.PostCommentRepository;
import com.codestates.hobby.domain.series.entity.Series;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostCommentServiceTest {
	@InjectMocks
	private PostCommentService postCommentService;

	@Mock
	static PostCommentRepository postCommentRepository;

	@Mock
	static MemberService memberService;

	@Mock
	static PostService postService;

	private static Member member;
	private static Category patentCategory;
	private static Category childCategory;
	private static Series series;
	private static Post post;
	private static List<String> urls = new ArrayList<>(Arrays.asList("url1","url2","url3"));

	@BeforeAll
	public static void init(){

		member = new Member("aaa@gmail.com",
				"홍길동",
				"Codestates11!",
				false);
		patentCategory = Category.createParent("운동","exercise");
		childCategory = Category.createChild("야구","baseball",patentCategory);
		series = new Series(member, "입문", childCategory, "Content");
		post = new Post(member,"Title",series,childCategory,"Content",urls);
	}

	@Nested
	class 댓글_생성{
		@Test
		void 회원_댓글_생성(){
			PostComment postComment = new PostComment("Comment", member, post);
			PostCommentDto.Post postDto = new PostCommentDto.Post();
			postDto.setContent("Comment");
			postDto.setProperties(1L,1L);

			given(postCommentRepository.save(any(PostComment.class))).willReturn(postComment);
			given(memberService.findVerifiedById(anyLong())).willReturn(member);
			given(postService.findVerifiedPost(anyLong())).willReturn(post);

			PostComment findComment = postCommentService.post(postDto);

			Assertions.assertEquals(findComment.getContent(),postDto.getContent());

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
			PostComment postComment = new PostComment("Comment", member, post);

			PostCommentDto.Patch patchDto = new PostCommentDto.Patch();
			patchDto.setContent("Comment");
			patchDto.setProperties(1L,1L,1L);

			given(postCommentRepository.save(any())).willReturn(postComment);
			given(postCommentRepository.findById(anyLong())).willReturn(Optional.of(postComment));
			given(memberService.findVerifiedById((anyLong()))).willReturn(member);

			PostComment findComment = postCommentService.update(patchDto);

			Assertions.assertEquals(findComment.getContent(),patchDto.getContent());
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
