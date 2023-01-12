package com.codestates.hobby.domain.showcase.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

class ShowcaseServiceTest {
	@Nested
	@DisplayName("쇼케이스 등록")
	class Post {
		void 쇼케이스를_작성한다() {
		}

		void 쇼케이스를_작성하면_구독자들에게_알림을_전송한다() {
		}

		void 비회원은_쇼케이스를_작성할수없다() {
		}

		void 쇼케이스는_이미지가_없으면_실패한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 수정")
	class Patch {
		void 쇼케이스를_수정한다() {
		}

		void 작성자가_아니면_실패한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 삭제")
	class Delete {
		void 쇼케이스를_삭제한다() {
		}

		void 작성자가_아니면_실패한다() {
		}
	}

	@Nested
	@DisplayName("쇼케이스 조회")
	class Find {
		void 회원의_쇼케이스_목록을_조회한다() {
		}

		void 카테고리의_쇼케이스_목록을_조회한다() {
		}

		void 검색어로_쇼케이스_목록을_조회한다() {
		}
	}
}