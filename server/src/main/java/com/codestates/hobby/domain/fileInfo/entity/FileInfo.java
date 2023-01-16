package com.codestates.hobby.domain.fileInfo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false, unique = true)
	private String fileURL;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", updatable = false)
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", updatable = false)
	private Series series;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_Id", updatable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showcase_id", updatable = false)
	private Showcase showcase;

	@Transient
	private static final Pattern pattern = Pattern.compile("([\\w\\-.]+)");

	@Transient
	private final List<String> urlTokens = new ArrayList<>(5);

	public FileInfo(String fileURL) {
		this.fileURL = fileURL;
		this.parse(fileURL);
	}

	private FileInfo(Member member, String fileURL) {
		this(fileURL);
		this.member = member;
	}

	private FileInfo(Series series, String fileURL) {
		this(fileURL);
		this.series = series;
	}

	private FileInfo(Showcase showcase, String fileURL) {
		this(fileURL);
		this.showcase = showcase;
	}

	private void parse(String fileURL) {
		Matcher matcher = pattern.matcher(fileURL);

		while (matcher.find())
			urlTokens.add(matcher.group());

		if (urlTokens.size() != 5)
			throw new IllegalArgumentException();
	}

	public String getBucket() {
		return urlTokens.get(2);
	}

	public String getBasePath() {
		return urlTokens.get(3);
	}

	public String getFilename() {
		return urlTokens.get(4);
	}

	public String getPath() {
		return String.join("/", urlTokens.get(3), urlTokens.get(4));
	}

	public static FileInfo createMemberImage(Member member, String fileURL) {
		return new FileInfo(member, fileURL);
	}

	public static FileInfo createSeriesImage(Series series, String fileURL) {
		return new FileInfo(series, fileURL);
	}

	public static FileInfo createShowcaseImage(Showcase showcase, String fileURL) {
		return new FileInfo(showcase, fileURL);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (Objects.equals(getFileURL(), o.toString()))
			return true;
		if (!(o instanceof FileInfo))
			return false;

		return Objects.equals(getFileURL(), ((FileInfo)o).getFileURL());
	}

	@Override
	public int hashCode() {
		return fileURL != null ? fileURL.hashCode() : 0;
	}
}
