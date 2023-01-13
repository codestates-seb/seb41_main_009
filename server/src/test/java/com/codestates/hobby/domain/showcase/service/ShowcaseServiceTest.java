package com.codestates.hobby.domain.showcase.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.repository.FileInfoRepository;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.entity.ShowcaseImage;
import com.codestates.hobby.domain.showcase.repository.ShowcaseImageRepository;
import com.codestates.hobby.domain.showcase.repository.ShowcaseRepository;
import com.codestates.hobby.utils.DatabaseCleanup;

@SpringBootTest
@ActiveProfiles({"gcs", "test"})
class ShowcaseServiceTest {
	@Autowired
	ShowcaseService service;

	@Autowired
	ShowcaseRepository showcaseRepository;

	@Autowired
	ShowcaseImageRepository imageRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FileInfoRepository fileInfoRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private DatabaseCleanup dbCleaner;

	@Nested
	@DisplayName("쇼케이스 등록")
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class Post {
		@BeforeAll
		void beforeAll() {
			dbCleaner.execute();
			init();
		}

		@Transactional
		void init() {
			memberRepository.save(new Member("email", "nickname", "password", false));
			categoryRepository.save(Category.createChild("취미", "hobby",
					categoryRepository.save(Category.createParent("그룹", "group"))
				)
			);
		}

		@Test
		void 쇼케이스를_작성한다() {
			// given
			List<String> urls = List.of("URL1", "URL2", "URL3");
			ShowcaseDto.Post post = new ShowcaseDto.Post();
			post.setMemberId(1L);
			post.setContent("Content");
			post.setCategory("취미");
			post.setImageUrls(urls);

			// when
			service.post(post);

			// then
			List<ShowcaseImage> images = imageRepository.findAll();
			assertEquals(3, images.size());
			assertIterableEquals(
				urls,
				images.stream().map(image -> image.getFileInfo().getFileURL()).collect(Collectors.toList())
			);
		}

		void 쇼케이스를_작성하면_구독자들에게_알림을_전송한다() {
			// Optional
		}

		void 비회원은_쇼케이스를_작성할수없다() {
			// After implementing authentication
		}
	}

	@Nested
	@DisplayName("쇼케이스 수정")
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class Patch {
		@BeforeAll
		void beforeAll() {
			dbCleaner.execute();
			init();
		}

		void init() {
			Member member = memberRepository.save(new Member("email", "nickname", "password", false));
			Category hobby = categoryRepository.save(Category.createChild("취미", "hobby",
					categoryRepository.save(Category.createParent("그룹", "group"))
				)
			);

			showcaseRepository.save(new Showcase("content", member, hobby, List.of("URL1", "URL2", "URL3")));
		}

		@Test
		void 쇼케이스를_수정한다() {
			// given
			ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
			patch.setContent("Modified Content");
			patch.setCategory("취미");
			patch.setMemberId(1L);
			patch.setShowcaseId(1L);
			patch.setImageUrls(new ArrayList<>(List.of("URL3", "URL4", "URL5")));

			// when
			Showcase showcase = service.update(patch);

			// then
			assertEquals("Modified Content", showcase.getContent());

			List<ShowcaseImage> images = imageRepository.findAllByShowcaseId(showcase.getId());
			assertEquals(3, images.size());
			assertIterableEquals(
				List.of("URL3", "URL4", "URL5"),
				images.stream().map(ShowcaseImage::getFileURL).collect(Collectors.toList())
			);

			List<FileInfo> fileInfos = fileInfoRepository.findAll();
			assertEquals(3, fileInfos.size());
			assertIterableEquals(
				List.of("URL3", "URL4", "URL5"),
				fileInfos.stream().map(FileInfo::getFileURL).collect(Collectors.toList())
			);
		}

		@Test
		void 작성자가_아니면_실패한다() {
			ShowcaseDto.Patch patch = new ShowcaseDto.Patch();
			patch.setCategory("취미");
			patch.setMemberId(2L);
			patch.setShowcaseId(1L);

			assertThrows(IllegalArgumentException.class, () -> service.update(patch));
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