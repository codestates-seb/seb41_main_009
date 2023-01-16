package com.codestates.hobby.domain.series.controller;

import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.mapper.SeriesMapper;
import com.codestates.hobby.domain.series.service.SeriesService;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;
import com.codestates.hobby.global.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping

public class SeriesController {
    private final SeriesService seriesService;
    private final SeriesMapper seriesMapper;

    @PostMapping(value = "/series")
    public ResponseEntity post(@RequestBody SeriesDto.Post post,
                               @AuthenticationPrincipal Long memberId) {
        post.setMemberId(memberId);

        Series series =  seriesService.create(post);

        log.info("\n\n--시리즈 생성--\n");
        return new ResponseEntity(series.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/series/{series-id}")
    public ResponseEntity patch(@PathVariable("series-id") long seriesId,
                                @RequestBody SeriesDto.Patch patch,
                                @AuthenticationPrincipal Long memberId) {
        patch.serProperties(memberId, seriesId);
        seriesService.edit(patch);

        log.info("\n\n--시리즈 수정--\n");
        return new ResponseEntity(seriesId, HttpStatus.OK);
    }

    @DeleteMapping("/series/{series-id}")
    public ResponseEntity delete(@PathVariable("series-id") long seriesId,
                                 @AuthenticationPrincipal Long memberId) {
        seriesService.delete(memberId, seriesId);

        log.info("\n\n--시리즈 삭제--\n");
        return new ResponseEntity(seriesId, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories/{category-name}/series")
    public ResponseEntity getAllByCategory(@PathVariable("category-name") String categoryName,
                                           CustomPageRequest pageRequest) {
        Page<Series> series = seriesService.findAllByCategory(categoryName, pageRequest.to());

        Page<SeriesDto.SimpleResponse> responses = series.map(seriesMapper::SeriesToSimpleResponseDto);

        log.info("\n\n--시리즈 리스트 조회--\n");
        return new ResponseEntity(new MultiResponseDto<>(responses), HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/series")
    public ResponseEntity getAllByMember(@PathVariable("member-id") long memberId,
                                         @AuthenticationPrincipal Long authId,
                                         CustomPageRequest pageRequest) {
        Page<Series> series = seriesService.findAllByMember(memberId, pageRequest.to());

        log.info("\n\n--시리즈 for 마이페이지--\n");
        return toResponseEntity(series, memberId);
    }

    private ResponseEntity toResponseEntity(Page<Series> series, Long memberId) {
        if(series.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            Page<SeriesDto.SimpleResponse> responses = series.map(seriesMapper::SeriesToSimpleResponseDto);
            responses.forEach(aSeries -> seriesMapper.setProperties(aSeries, memberId));
            return new ResponseEntity(new MultiResponseDto<>(responses), HttpStatus.OK);
        }
    }
}