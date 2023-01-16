package com.codestates.hobby.domain.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.repository.CategoryRepository;
import com.codestates.hobby.domain.stub.CategoryStub;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
	@InjectMocks
	CategoryService service;

	@Mock
	CategoryRepository repository;

	@Test
	void 아이디로_조회한다() {
		given(repository.findById(anyLong())).willReturn(Optional.of(CategoryStub.group()));

		assertDoesNotThrow(() -> service.findById(1L));
	}

	@Test
	void 이름으로_조회한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willReturn(Optional.of(CategoryStub.group()));
		given(repository.findByGroupIsNotNullAndName(anyString())).willReturn(Optional.of(CategoryStub.hobby()));

		Category group = assertDoesNotThrow(() -> service.findGroupByName("그룹1"));
		Category hobby = assertDoesNotThrow(() -> service.findHobbyByName("취미1"));

		assertTrue(group.isGroup());
		assertFalse(hobby.isGroup());
	}

	@Test
	void 취미로_그룹을_조회하면_실패한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findGroupByName("HOBBY1"));
	}

	@Test
	void 그룹으로_취미를_조회하면_실패한다() {
		given(repository.findByGroupIsNotNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findHobbyByName("그룹1"));
	}

	@Test
	void 전체를_조회한다() {
		given(repository.findAll()).willReturn(List.of(CategoryStub.group()));

		assertEquals(service.findAll().size(), 1);
	}

	@Test
	void 그룹목록을_조회한다() {
		given(repository.findAllByGroupIsNull()).willReturn(List.of(CategoryStub.group()));
		given(repository.findAllGroupsWithHobbies()).willReturn(List.of(CategoryStub.group()));

		assertEquals(service.findAllGroups().size(), 1);
		assertEquals(service.findAllGroupsWithHobbies().size(), 1);
	}

	@Test
	void 그룹명으로_취미목록을_조회한다() {
		given(repository.findAllByGroup(any())).willReturn(List.of(CategoryStub.hobby(), CategoryStub.hobby()));
		given(repository.findByGroupIsNullAndName(anyString())).willReturn(Optional.of(CategoryStub.group()));

		assertEquals(service.findAllByGroup("group").size(), 2);
	}

	@Test
	void 취미로_취미목록을_조회하면_실패한다() {
		given(repository.findByGroupIsNullAndName(anyString())).willThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> service.findAllByGroup("HOBBY"));
	}
}
