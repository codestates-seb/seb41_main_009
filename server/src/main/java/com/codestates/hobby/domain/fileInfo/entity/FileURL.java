package com.codestates.hobby.domain.fileInfo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.codestates.hobby.domain.fileInfo.dto.ImageType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileURL {
	private static final Pattern PATTERN = Pattern.compile("([\\w\\-.]+)");

	@Getter
	@Column(unique = true, nullable = false, updatable = false)
	private String fileUrl;

	@Transient
	private final List<String> urlTokens = new ArrayList<>(5);

	public FileURL(String fileUrl) {
		this.parse(fileUrl);
		this.fileUrl = fileUrl;
	}

	private void parse(String fileURL) {
		Matcher matcher = PATTERN.matcher(fileURL);

		while (matcher.find())
			urlTokens.add(matcher.group());

		if (urlTokens.size() != 5)
			throw new IllegalArgumentException();

		urlTokens.add(fileURL.substring(fileURL.lastIndexOf('.') + 1));
	}

	public String getToken(FileInfo.TOKEN token) {
		if (urlTokens.isEmpty()) parse(fileUrl);

		return token == FileInfo.TOKEN.PATH
			? getPath()
			: urlTokens.get(token.getIndex());
	}

	public ImageType getImageType() {
		return ImageType.search(getToken(FileInfo.TOKEN.EXTENSION));
	}

	private String getPath() {
		return String.join("/", urlTokens.get(3), urlTokens.get(4));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (Objects.equals(fileUrl, o.toString()))
			return true;
		if (getClass() != o.getClass())
			return false;

		FileURL fileURL = (FileURL)o;
		return Objects.equals(fileUrl, fileURL.fileUrl);
	}

	@Override
	public int hashCode() {
		return fileUrl != null ? fileUrl.hashCode() : 0;
	}
}
