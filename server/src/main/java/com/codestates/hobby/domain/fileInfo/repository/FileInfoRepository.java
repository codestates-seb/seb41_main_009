package com.codestates.hobby.domain.fileInfo.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codestates.hobby.domain.fileInfo.dto.ImageProjection;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	boolean existsByFileURL_FileUrl(String fileUrl);

	Optional<FileInfo> findByFileURL_FileUrl(String fileUrl);

	@Query(value = "select f.file_url as fileURL, f.showcase_id as entityId from file_info f inner join "
		+ "(select min(ff.file_index) as idx, ff.showcase_id as s_id from file_info ff where ff.showcase_id in :ids group by ff.showcase_id) as ff "
		+ "on ff.s_id = f.showcase_id and ff.idx = f.file_index", nativeQuery = true)
	Set<ImageProjection> findAllThumbnailByEntityAndIndexIn(Set<Long> ids);
}
