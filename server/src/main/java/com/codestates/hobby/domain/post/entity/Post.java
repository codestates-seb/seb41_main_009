package com.codestates.hobby.domain.post.entity;

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

import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.series.entity.Series;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "MEDIUMTEXT")
	private String content;

	@Column
	@ColumnDefault("0")
	private int views;

	@Column
	@ColumnDefault("false")
	private boolean isTemp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", nullable = false)
	private Series series;

	@OneToMany
	@JoinTable(name = "POST_IMAGE",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "file_info_id"))
	private List<FileInfo> fileInfos;

	@OneToMany(mappedBy = "post")
	private List<PostComment> comments;

	public Post(Member member, String title, Category category, String content) {
		this(member, title, null, content, category);
	}

	public Post(Member member, String title, Series series, String content, Category category) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.category = category;
		this.series = series;
	}

	public void addFile(FileInfo info) {
		this.fileInfos.add(info);
	}
}
