package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
//    @Query("select p from Post p join fetch p.member m join fetch m.image join fetch m.roles where p.id = :postId")
    Optional<Post> findById(@Param("postId") long postId);

//    @Query(value = "select p from Post p join fetch p.member m join fetch m.image join fetch m.roles order by p.id desc",
//            countQuery = "select p from Post p")
    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

//    @Query(value = "select p from Post p join fetch p.member m join fetch m.image join fetch m.roles where p.member.id = :memberId order by p.id desc",
//            countQuery = "select p from Post p where p.member.id = :memberId")
    Page<Post> findAllByMemberIdOrderByIdDesc(@Param("memberId")long memberId, Pageable pageable);

//    @Query(value = "select p from Post p join fetch p.member m join fetch m.image join fetch m.roles where p.category = :category order by p.id desc",
//            countQuery = "select p from Post p where p.category = :category")
    Page<Post> findAllByCategoryOrderByIdDesc(@Param("category")Category category, Pageable pageable);

//    @Query(value = "select p from Post p join fetch p.member m join fetch m.image join fetch m.roles where p.series.id = :seriesId order by p.id desc",
//            countQuery = "select p from Post p where p.series.id = :seriesId")
    Page<Post> findAllBySeriesIdOrderByIdDesc(@Param("seriesId")long seriesId, Pageable pageable);

//    @Query(value = "select p from Post p join fetch p.member m join fetch m.image join fetch m.roles where p.content like concat('%',:content,'%') order by p.id desc",
//            countQuery = "select p from Post p where p.content like concat('%',:content,'%')")
    Page<Post> findAllByContentContainsOrderByIdDesc(@Param("content")String content, Pageable pageable);
}
