package com.codestates.hobby.domain.showcase.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SHOWCASE_IMAGE",
		joinColumns = @JoinColumn(name = "showcase_id"),
		inverseJoinColumns = @JoinColumn(name = "file_info_id"))
	private List<FileInfo> fileInfos = new ArrayList<>();

	@OneToMany(mappedBy = "showcase")
	private List<ShowcaseComment> comments;

	public Showcase(String content, Member member, Category category) {
		this.member = member;
		this.content = content;
		this.category = category;
	}

	public void addFile(FileInfo info) {
		this.fileInfos.add(info);
	}
}