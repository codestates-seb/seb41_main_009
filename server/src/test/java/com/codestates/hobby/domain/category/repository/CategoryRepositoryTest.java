package com.codestates.hobby.domain.category.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.SessionFactory;
import org.hibernate.stat.CacheRegionStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoryRepositoryTest {
	@Autowired
	CategoryRepository repository;

	@Autowired
	SessionFactory sf;

	@Autowired
	TestEntityManager em;

	CacheRegionStatistics query, entity;

	@BeforeEach
	void beforeEach() {
		sf.getStatistics().clear();
		sf.getCache().evictAllRegions();
		query = sf.getStatistics().getCacheRegionStatistics("default-query-results-region");
		entity = sf.getStatistics().getCacheRegionStatistics("category");
	}

	@Test
	@Transactional(readOnly = true)
	void findById() {
		repository.findById(1L);
		assertEquals(1, entity.getPutCount());

		em.clear();

		repository.findById(1L);
		assertEquals(1, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findByKorNameOrEngName() {
		repository.findAllByGroupIsNull();
		assertEquals(1, query.getPutCount());
		assertEquals(6, entity.getPutCount());

		em.clear();

		repository.findAllByGroupIsNull();
		assertEquals(1, query.getHitCount());
		assertEquals(6, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findByGroupIsNullAndName() {
		Category cat = repository.findByGroupIsNullAndName("sports").get();
		assertEquals(1, entity.getPutCount());

		em.clear();
		repository.findById(cat.getId());
		assertEquals(1, entity.getHitCount());

		em.clear();
		repository.findByGroupIsNullAndName("sports");
		assertEquals(2, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findByGroupIsNotNullAndName() {
		repository.findByGroupIsNotNullAndName("soccer");
		assertEquals(1, query.getPutCount());
		assertEquals(1, entity.getPutCount());

		em.clear();

		repository.findByGroupIsNotNullAndName("soccer");
		assertEquals(1, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findAllByGroupIsNull() {
		repository.findAllByGroupIsNull();
		assertEquals(1, query.getPutCount());
		assertEquals(6, entity.getPutCount());

		em.clear();

		repository.findAllByGroupIsNull();
		assertEquals(1, query.getHitCount());
		assertEquals(6, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findAllByGroup() {
		Category group = repository.findByKorNameOrEngName("sports").get();

		em.clear();
		repository.findAllByGroup(group);
		assertEquals(2, query.getPutCount());
		assertEquals(4, entity.getPutCount());

		em.clear();
		repository.findAllByGroup(group);
		assertEquals(1, query.getHitCount());
		assertEquals(3, entity.getHitCount());
	}

	@Test
	@Transactional(readOnly = true)
	void findAll() {
		int size = repository.findAll().size();
		assertEquals(size, entity.getPutCount());
	}
}