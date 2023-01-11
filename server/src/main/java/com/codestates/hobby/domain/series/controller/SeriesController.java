package com.codestates.hobby.domain.series.controller;

import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.mapper.SeriesMapper;
import com.codestates.hobby.domain.series.service.SeriesService;
import com.codestates.hobby.global.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping

public class SeriesController {
    private final SeriesService seriesService;
    private final SeriesMapper seriesMapper;

    @PostMapping(value = "/series", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity post(@RequestPart(value = "request") SeriesDto.Post post,
                                     @RequestPart(value = "thumbnail") MultipartFile thumbnail) {
        post.setThumbnail(thumbnail);
        Series series =  seriesService.create(seriesMapper.PostDtoToSeries(post));

        log.info("\n\n--시리즈 생성--\n");
        return new ResponseEntity(series.getId(), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/series/{series-id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity patch(@PathVariable("series-id") long seriesId,
                                      @RequestPart(value = "request") SeriesDto.Patch patch,
                                      @RequestPart(value = "thumbnail") MultipartFile thumbnail) {
        patch.setThumbnail(thumbnail);
        seriesService.edit(seriesId, seriesMapper.PatchDtoToSeries(patch));

        log.info("\n\n--시리즈 수정--\n");
        return new ResponseEntity(seriesId, HttpStatus.OK);
    }

    @DeleteMapping("/series/{series-id}")
    public ResponseEntity delete(@PathVariable("series-id") long seriesId) {
        seriesService.delete(seriesId);

        log.info("\n\n--시리즈 삭제--\n");
        return new ResponseEntity(seriesId, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories/{category-name}/series")
    public ResponseEntity getAllByCategory(@PathVariable("category-name") String categoryName,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "1") int size,
                                           @RequestParam(defaultValue = "NEWEST") String sort) {
        Page<Series> series = seriesService.findAllByCategory(categoryName, page, size, sort.equalsIgnoreCase("NEWEST"));

        log.info("\n\n--시리즈 리스트 조회--\n");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/series")
    public ResponseEntity getAllByMember(@PathVariable("member-id") long memberId,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "1") int size,
                                         @RequestParam(defaultValue = "NEWEST") String sort){
        Page<Series> series = seriesService.findAllByMember(memberId, page, size, sort.equalsIgnoreCase("NEWEST"));

        log.info("\n\n--마이페이지 조회시 전달할 시리즈 리스트--\n");
        return new ResponseEntity(HttpStatus.OK);
    }
}