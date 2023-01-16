package com.codestates.hobby.domain.series.mapper;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SeriesMapper {

    SeriesDto.Response SeriesToResponseDto(Series series);
    SeriesDto.SimpleResponse SeriesToSimpleResponseDto(Series series);

    default String toString(Category value) {
        return value.getKorName();
    }
}
