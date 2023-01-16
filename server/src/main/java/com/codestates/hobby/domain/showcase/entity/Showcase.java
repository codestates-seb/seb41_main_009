package com.codestates.hobby.domain.showcase.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Showcase extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "showcase", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<FileInfo> images = new ArrayList<>();

	@OneToMany(mappedBy = "showcase")
	private List<ShowcaseComment> comments = new ArrayList<>();

	public Showcase(String content, Member member, Category category, List<String> imageURLs) {
		this.content = content;
		this.member = member;
		this.category = category;

		imageURLs.forEach(this::addImageFromUrl);
	}

	public boolean isWrittenBy(Long memberId) {
		return Objects.equals(memberId, member.getId());
	}

	public void addImageFromUrl(String url) {
		images.add(FileInfo.createShowcaseImage(this, url));
	}

	public void update(Category category, String content, List<String> urls) {
		if (!Objects.equals(this.category.getId(), category.getId()))
			this.category = category;

		this.content = content;
		this.updateImage(urls);
	}

	private void updateImage(List<String> urls) {
		List<String> olds = images.stream().map(FileInfo::getFileURL).collect(Collectors.toList());

		images.retainAll(urls);

		urls.stream()
			.filter(url -> !olds.contains(url))
			.forEach(this::addImageFromUrl);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Showcase))
			return false;

		return Objects.equals(getId(), ((Showcase)o).getId());
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}