package com.codestates.hobby.domain.post.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.series.entity.Series;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	private Series series;

	@OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<FileInfo> images = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<PostComment> comments = new ArrayList<>();

	public Post(Member member, String title, Category category, String content, List<String> imageURLs) {
		this(member, title, null, category, content, imageURLs);
	}

	public Post(Member member, String title, Category category, String content) {
		this(member, title, null, category, content,null);
	}

	public Post(Member member, String title, Series series,Category category, String content) {
		this(member,title,series,category,content,null);
	}

	public Post(Member member, String title, Series series, Category category, String content, List<String> imageURLs) {
		this.member = member;
		this.title = title;
		this.content = content;
		this.category = category;
		this.series = series;

		if (imageURLs == null) this.images = null;
		else imageURLs.forEach(this::addImageFromUrl);


	}

	public void updatePost (String title, String content, Category category, Series series, List<String> imageURLs) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.series = series;
		updateImage(imageURLs);
	}

	public void updatePost (String title, String content, Category category, Series series) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.series = series;
	}

	public void updatePost (String title, String content, Category category, List<String> imageURLs) {
		this.title = title;
		this.content = content;
		this.category = category;
		updateImage(imageURLs);
	}

	public void updatePost (String title, String content, Category category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public void addImageFromUrl(String url) {
		FileInfo fileInfo = new FileInfo(this, url, 0);
		images.add(fileInfo);
	}

	public void  updateImage(List<String> urls){
		List<String> olds = images.stream().map(FileInfo::getFileURL).collect(Collectors.toList());
		new ArrayList<>(images).stream().filter(image -> !urls.contains(image.getFileURL())).forEach(image -> images.remove(image));
		urls.stream().filter(url -> !olds.contains(url)).forEach(this::addImageFromUrl);
	}
}
