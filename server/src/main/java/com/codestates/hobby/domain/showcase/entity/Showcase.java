package com.codestates.hobby.domain.showcase.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@Transient
	private ShowcaseComment lastComment;

	@OrderBy("fileIndex asc")
	@BatchSize(size = 100)
	@OneToMany(mappedBy = "showcase", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<FileInfo> fileInfos = new ArrayList<>();

	@OrderBy("id desc")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@OneToMany(mappedBy = "showcase", orphanRemoval = true)
	private List<ShowcaseComment> comments = new ArrayList<>();

	public Showcase(String content, Member member, Category category, List<FileInfo> imageURLs) {
		this.content = content;
		this.member = member;
		this.category = category;

		imageURLs.forEach(this::addImage);
	}

	public boolean isWrittenBy(Long memberId) {
		return Objects.equals(memberId, member.getId());
	}

	public void addImage(FileInfo info) {
		FileInfo newInfo = new FileInfo(this, info.getFileURL(), info.getFileIndex());
		Optional.ofNullable(info.getSignedURL())
			.ifPresent(newInfo::setSignedURL);
		fileInfos.add(newInfo);
	}

	public void update(Category category, String content, List<FileInfo> infos) {
		if (!Objects.equals(this.category.getId(), category.getId()))
			this.category = category;

		this.content = content;
		this.updateImage(infos);
	}

	private void updateImage(List<FileInfo> newInfos) {
		fileInfos.retainAll(newInfos);

		newInfos.forEach(info -> {
			int idx = fileInfos.indexOf(info);
			if (idx == -1)
				addImage(info);
			else
				fileInfos.get(idx).updateIndex(info.getFileIndex());
		});
	}

	public void setLastComment(ShowcaseComment comment) {
		lastComment = comment;
	}

	public void setComments(List<ShowcaseComment> comments) {
		this.comments = comments;
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