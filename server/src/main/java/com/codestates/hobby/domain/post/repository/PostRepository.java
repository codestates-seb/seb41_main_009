package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

    Page<Post> findAllByCategoryOrderByIdDesc(Category category, Pageable pageable);

    Page<Post> findAllBySeriesIdOrderByIdDesc(long seriesId, Pageable pageable);

    Page<Post> findAllByContentContainsOrderByIdDesc(String content, Pageable pageable);
}
