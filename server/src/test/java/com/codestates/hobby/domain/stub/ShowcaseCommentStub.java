package com.codestates.hobby.domain.stub;

import java.time.LocalDateTime;

import com.codestates.hobby.domain.showcase.dto.ShowcaseCommentDto;

public class ShowcaseCommentStub {
	public static ShowcaseCommentDto.Response createResponse() {
		ShowcaseCommentDto.Response response = new ShowcaseCommentDto.Response();
		response.setId(1L);
		response.setWriter(null);
		response.setItWriter(false);
		response.setContent("쇼케이스 댓글");
		response.setCreatedAt(LocalDateTime.now());
		response.setModifiedAt(LocalDateTime.now());
		return response;
	}
}
