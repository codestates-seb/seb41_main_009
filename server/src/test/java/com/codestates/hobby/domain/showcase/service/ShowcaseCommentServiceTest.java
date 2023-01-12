package com.codestates.hobby.domain.showcase.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import com.codestates.hobby.utils.ControllerTest;

class ShowcaseCommentServiceTest extends ControllerTest {
	@Nested
	@DisplayName("쇼케이스 댓글 등록")
	class Post {
		void 댓글을_등록한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 댓글 수정")
	class Patch {
		void 댓글을_수정한다() {
		}

		void 작성자가_아니면_실패한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 댓글 삭제")
	class Delete {
		void 댓글을_삭제한다() {
		}

		void 작성자가_아니면_실패한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 댓글 조회")
	class Find {
		void 쇼케이스의_댓글수를_조회한다() {
		}

		void 쇼케이스의_댓글목록을_조회한다() {
		}

		void 회원의_쇼케이스_댓글목록을_조회한다() {
		}
	}

}