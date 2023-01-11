package com.codestates.hobby.domain.fileInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
