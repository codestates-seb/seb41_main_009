package com.codestates.hobby.domain.fileInfo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	boolean existsByFileURL_FileUrl(String fileUrl);

	Optional<FileInfo> findByFileURL_FileUrl(String fileUrl);
}
