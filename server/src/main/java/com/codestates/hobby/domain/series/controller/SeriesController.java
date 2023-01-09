package com.codestates.hobby.domain.series.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping

public class SeriesController {
    @PostMapping("/series")
    public ResponseEntity postSeries() {
        log.info("\n--시리즈 생성--\n");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/series/{series-id}")
    public ResponseEntity patchSeries(@PathVariable("series-id") long seriesId) {
        log.info("\n--시리즈 수정--\n");
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/series/{series-id}")
    public ResponseEntity deleteSeries(@PathVariable("series-id") long seriesId) {
        log.info("\n--시리즈 삭제--\n");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories/{category-name}/series")
    public ResponseEntity getSeries(@PathVariable("category-name") String categoryName) {
        log.info("\n--시리즈 리스트 조회--\n");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{series-id}/posts")
    public ResponseEntity getSeriesPost(@PathVariable("series-id") long seriesId) {
        log.info("\n--시리즈에 포함된 포스트들 조회--\n");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/series")
    public ResponseEntity getSeriesByMember(@PathVariable("member-id") long memberId) {
        log.info("\n--마이페이지 조회시 전달할 시리즈 리스트--\n");
        return new ResponseEntity(HttpStatus.OK);
    }
}
//시리즈 생성
//시리즈 수정(하)
//시리즈 삭제(하)
//시리즈 리스트 불러오기
//시리즈에 포함된 포스트 리스트 불러오기
//멤버 조회시 전달할 시리즈 리스트