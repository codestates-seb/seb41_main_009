package com.codestates.hobby.domain.category.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryServiceTest {
	@Autowired
	CategoryRepository repository;

	@Autowired
	CategoryService service;

	@BeforeAll
	void beforeAll() {
		deleteAll();
		setUp();
	}

	@AfterAll
	void afterAll() {
		deleteAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void setUp() {
		Category group1 = repository.save(Category.createParent("그룹1", "group1"));
		Category group2 = repository.save(Category.createParent("그룹2", "group2"));

		repository.saveAll(List.of(
			Category.createChild("취미1", "hobby1", group1),
			Category.createChild("취미2", "hobby2", group1),
			Category.createChild("취미3", "hobby3", group1),
			Category.createChild("취미4", "hobby4", group2),
			Category.createChild("취미5", "hobby5", group2),
			Category.createChild("취미6", "hobby6", group2)));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void deleteAll() {
		repository.deleteAll();
	}

	@Test
	void 아이디로_조회한다() {
		assertDoesNotThrow(() -> service.findById(1L));
	}

	@Test
	void 이름으로_조회한다() {
		Category cat = assertDoesNotThrow(() -> service.findHobbyByName("취미1", true));
		Category group = assertDoesNotThrow(() -> service.findGroupByName("그룹1", true));

		assertNotNull(cat.getGroup());
		assertNull(group.getGroup());
		assertEquals(group.getId(), cat.getGroup().getId());
	}

	@Test
	void 취미로_그룹을_조회하면_실패한다() {
		assertThrows(IllegalArgumentException.class, () -> service.findGroupByName("HOBBY1", false));
	}

	@Test
	void 그룹으로_취미를_조회하면_실패한다() {
		assertThrows(IllegalArgumentException.class, () -> service.findHobbyByName("그룹1", true));
	}

	@Test
	void 전체를_조회한다() {
		assertEquals(service.findAll().size(), 8);
		assertEquals(service.findAllGroups().size(), 2);
	}

	@Test
	void 그룹목록을_조회한다() {
		assertEquals(service.findAllGroups().size(), 2);
	}

	@Test
	void 그룹명으로_취미목록을_조회한다() {
		assertEquals(service.findAllByGroup("group1").size(), 3);
	}

	@Test
	void 취미로_취미목록을_조회하면_실패한다() {
		assertThrows(IllegalArgumentException.class, () -> service.findAllByGroup("HOBBY1"));
	}
}
