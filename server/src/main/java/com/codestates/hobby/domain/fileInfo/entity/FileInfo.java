package com.codestates.hobby.domain.fileInfo.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private FileURL fileURL;

	@Transient
	private String signedURL;

	private Integer index;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Setter
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	private Series series;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_Id", updatable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showcase_id", updatable = false)
	private Showcase showcase;

	public FileInfo(String fileURL, String signedURL, Integer index) {
		this.fileURL = new FileURL(fileURL);
		this.signedURL = signedURL;
		this.index = index;
	}

	public FileInfo(Member member, String fileURL, int index) {
		this.fileURL = new FileURL(fileURL);
		this.member = member;
		this.index = index;
	}

	public FileInfo(Series series, String fileURL, int index) {
		this.fileURL = new FileURL(fileURL);
		this.series = series;
		this.index = index;
	}

	public FileInfo(Post post, String fileURL, int index) {
		this.fileURL = new FileURL(fileURL);
		this.index = index;
		this.post = post;
	}

	public FileInfo(Showcase showcase, String fileURL, int index) {
		this.fileURL = new FileURL(fileURL);
		this.showcase = showcase;
		this.index = index;
	}

	public String getToken(TOKEN token) {
		return fileURL.getToken(token);
	}

	public String getFileURL() {
		return fileURL.getFileUrl();
	}

	public ImageType getImageType() {
		return fileURL.getImageType();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof FileInfo))
			return false;

		return Objects.equals(getFileURL(), ((FileInfo)o).getFileURL());
	}

	@Override
	public int hashCode() {
		return fileURL != null ? fileURL.hashCode() : 0;
	}

	public void updateIndex(int index) {
		this.index = index;
	}

	public void setSignedURL(String url) {
		this.signedURL = url;
	}

	public enum TOKEN {
		DOMAIN(1), BUCKET(2), BASEPATH(3), FILENAME(4), EXTENSION(5), PATH(6);

		@Getter
		private final int index;

		TOKEN(int index) {
			this.index = index;
		}
	}
}
