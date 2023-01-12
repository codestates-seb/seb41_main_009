package com.codestates.hobby.domain.fileInfo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

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

	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public FileInfo(String fileURL) {
		this.fileURL = fileURL;
	}

	public String getBucket() {
		return fileURL.substring(0, fileURL.indexOf('/'));
	}

	public String getFilename() {
		return fileURL.substring(fileURL.indexOf('/') + 1);
	}
}
