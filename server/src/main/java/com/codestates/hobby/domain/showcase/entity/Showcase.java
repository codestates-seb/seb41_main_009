package com.codestates.hobby.domain.showcase.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@OneToMany
	@JoinTable(name = "SHOWCASE_IMAGE",
		joinColumns = @JoinColumn(name = "showcase_id"),
		inverseJoinColumns = @JoinColumn(name = "file_info_id", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (file_info_id) references file_info ON DELETE CASCADE")))
	private List<FileInfo> fileInfos = new ArrayList<>();

	@OneToMany(mappedBy = "showcase")
	private List<ShowcaseComment> comments;

	public Showcase(String content, Member member, Category category, List<FileInfo> fileInfos) {
		this.content = content;
		this.member = member;
		this.category = category;
		this.fileInfos = fileInfos;
	}

	public void addFile(FileInfo info) {
		this.fileInfos.add(info);
	}

	public boolean isWrittenBy(Long memberId) {
		return Objects.equals(memberId, member.getId());
	}

	public void update(List<FileInfo> newFiles, Category category, String content) {
		if (!Objects.equals(this.category.getId(), category.getId()))
			this.category = category;

		if (!this.content.equals(content))
			this.content = content;

		this.fileInfos.clear();
		this.fileInfos.addAll(newFiles);
	}
}