package com.codestates.hobby.domain.fileInfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.codestates.hobby.domain.common.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private String fileName;

	@Column(nullable = false, updatable = false, unique = true)
	private String savedFileName;

	@Column(nullable = false)
	private int size;

	public FileInfo(String fileName, String savedFileName, int size) {
		this.savedFileName = savedFileName;
		this.fileName = fileName;
		this.size = size;
	}
}
