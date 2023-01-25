package com.codestates.hobby.domain.post.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.persistence.*;

import com.codestates.hobby.domain.common.Writing;
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
@DiscriminatorValue("Post")
@NoArgsConstructor
public class Post extends Writing {

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
		super(member, title, category, content);
		this.series = series;

		if (imageURLs == null) this.images = null;
		else imageURLs.forEach(this::addImageFromUrl);
	}

	public void updatePost (String title, String content, Category category, Series series, List<String> imageURLs) {
		super.update(title, content, category);
		this.series = series;

		if (imageURLs == null) this.images = null;
		else updateImage(imageURLs);
	}

	public void updatePost (String title, String content, Category category, Series series) {
		this.updatePost(title, content, category, series, null);
	}

	public void updatePost (String title, String content, Category category, List<String> imageURLs) {
		this.updatePost(title, content, category, null, imageURLs);
	}

	public void updatePost (String title, String content, Category category) {
		this.updatePost(title, content, category, null, null);

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