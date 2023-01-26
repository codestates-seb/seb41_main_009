package com.codestates.hobby.domain.stub;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;

public class ShowcaseStub {
	public static ShowcaseDto.Post createPost() {
		ShowcaseDto.Post post = new ShowcaseDto.Post();
		post.setContent("content");
		post.setCategory("soccer");
		post.setFileInfos(FileInfoStub.createRequests(0, 3));
		return post;
	}

	public static ShowcaseDto.Patch createPatch() {
		ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
		patch.setContent("modified-content");
		patch.setCategory("soccer");
		patch.setFileInfos(FileInfoStub.createRequests(2, 3));
		return patch;
	}

	public static ShowcaseDto.Response createResponse() {
		ShowcaseDto.Response response = new ShowcaseDto.Response();
		response.setId(1L);
		response.setContent("content");
		response.setCategory("soccer");
		response.setCreatedAt(LocalDateTime.now());
		response.setModifiedAt(LocalDateTime.now());
		response.setImageUrls(FileInfoStub.createResponses(0, 3));
		response.setComments(List.of(ShowcaseCommentStub.createResponse()));
		response.setWriter(new MemberDto.SimpleResponse());
		return response;
	}

	public static ShowcaseDto.SimpleResponse createSimpleResponse() {
		ShowcaseDto.SimpleResponse response = new ShowcaseDto.SimpleResponse();
		response.setId(1L);
		response.setContent("content");
		response.setCategory("soccer");
		response.setComments(3);
		response.setCreatedAt(LocalDateTime.now());
		response.setModifiedAt(LocalDateTime.now());
		response.setThumbnailUrl("URL1");
		response.setWriter(new MemberDto.SimpleResponse());
		return response;
	}

	public static Page<Showcase> createPage() {
		return new PageImpl(List.of(createShowcase()));
	}

	public static Showcase createShowcase() {
		Showcase showcase = new Showcase("content", null, null, List.of(FileInfoStub.createEntity(true)));
		ReflectionTestUtils.setField(showcase, "id", 1L);
		return showcase;
	}

	public static Showcase updateShowcase() {
		Showcase showcase = new Showcase("content", null, null, List.of(FileInfoStub.createEntity(false)));
		ReflectionTestUtils.setField(showcase, "id", 1L);
		return showcase;
	}
}
