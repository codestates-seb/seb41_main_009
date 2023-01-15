package com.codestates.hobby.domain.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;
import com.codestates.hobby.global.config.CacheConfig;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {CacheConfig.class, CategoryService.class})
class CategoryServiceTest {
	@MockBean
	CategoryRepository repository;

	@Autowired
	CategoryService service;

	@Autowired
	CacheManager cm;

	Category group = Category.createParent("운동", "sport");
	Category hobby = Category.createChild("축구", "soccer", group);

	@BeforeEach
	void clear() {
		cm.getCache("category").clear();
	}

	@Test
	void 아이디로_조회한다() {
		given(repository.findById(anyLong())).willReturn(Optional.of(group));

		assertDoesNotThrow(() -> service.findById(1L));
		assertNotNull(getFromCache(1L));
	}

	@Test
	void 이름으로_조회한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willReturn(Optional.of(group));
		given(repository.findByGroupIsNotNullAndName(anyString())).willReturn(Optional.of(hobby));

		assertDoesNotThrow(() -> service.findGroupByName("그룹"));
		assertDoesNotThrow(() -> service.findHobbyByName("취미"));

		assertNotNull(getFromCache("취미"));
		assertNotNull(getFromCache("그룹"));
	}

	@Test
	void 취미로_그룹을_조회하면_실패한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findGroupByName("HOBBY"));
		assertEquals(0, getSetFromCache().size());
	}

	@Test
	void 그룹으로_취미를_조회하면_실패한다() {
		given(repository.findByGroupIsNotNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findHobbyByName("그룹"));
		assertEquals(0, getSetFromCache().size());
	}

	@Test
	void 전체를_조회한다() {
		given(repository.findAll()).willReturn(List.of(group, hobby));

		assertEquals(2, service.findAll().size());
		assertEquals(1, getSetFromCache().size());
		assertEquals(2, getAllFromCache("findAll").size());
	}

	@Test
	void 그룹목록을_조회한다() {
		given(repository.findAllByGroupIsNull()).willReturn(List.of(group, group));

		assertEquals(2, service.findAllGroups().size());
		assertEquals(1, getSetFromCache().size());
		assertEquals(2, getAllFromCache("findAllGroups").size());
	}

	@Test
	void 그룹명으로_취미목록을_조회한다() {
		// 메서드가 동일한 클래스 내의 다른 메서드를 호출할 때 캐싱하지 않는 이유
		// https://stackoverflow.com/questions/12115996/spring-cache-cacheable-method-ignored-when-called-from-within-the-same-class

		given(repository.findAllByGroup(any())).willReturn(List.of(hobby, hobby));
		given(repository.findByGroupIsNullAndName(anyString())).willReturn(Optional.of(group));

		assertEquals(2, service.findAllByGroup("group").size());
		assertEquals(1, getSetFromCache().size());
		assertEquals(2, getAllFromCache("findAllBygroup").size());
	}

	@Test
	void 취미로_취미목록을_조회하면_실패한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findAllByGroup("HOBBY"));
		assertNull(getAllFromCache("findAllBygroup"));
		assertEquals(0, getSetFromCache().size());
	}

	private Category getFromCache(Object obj) {
		return cm.getCache("category").get(obj, Category.class);
	}

	private List getAllFromCache(Object obj) {
		return cm.getCache("category").get(obj, List.class);
	}

	private Set<@NonNull Object> getSetFromCache() {
		CaffeineCache caffeineCache = (CaffeineCache)cm.getCache("category");
		com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
		return nativeCache.asMap().keySet();
	}
}
