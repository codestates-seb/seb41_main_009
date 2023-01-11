package com.codestates.hobby.domain.series.service;

import com.codestates.hobby.domain.series.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SeriesService {
    public Series create(Series series) {
        return series;
    }

    public Series edit(long seriesId, Series series) {
        return series;
    }

    public void delete(Long seriesId) {

    }

    public Page<Series> findAllByCategory(String categoryName, int page, int size, boolean isNewest) {
        //해당 카테고리에 속한 모든 시리즈 조회
        //sorting
        return null;
    }

    public Page<Series> findAllByMember(long memberId, int page, int size, boolean isNewest) {
        return null;
    }

    public Series findById(Long seriesId) {
        //loginMember()와 시리즈 작성 멤버가 다를 경우 조회수 증가
        return null;
    }
}
