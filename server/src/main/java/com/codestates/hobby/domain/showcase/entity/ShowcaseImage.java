package com.codestates.hobby.domain.showcase.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"showcase_id", "file_info_id"}))
public class ShowcaseImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showcase_id", nullable = false, updatable = false)
	private Showcase showcase;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "file_info_id", nullable = false, updatable = false)
	private FileInfo fileInfo;

	public ShowcaseImage(Showcase showcase, FileInfo fileInfo) {
		this.showcase = showcase;
		this.fileInfo = fileInfo;
	}

	public String getFileURL() {
		return fileInfo.getFileURL();
	}
}
