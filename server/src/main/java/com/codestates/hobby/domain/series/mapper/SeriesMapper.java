package com.codestates.hobby.domain.series.mapper;

import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeriesMapper {
    Series PostDtoToSeries(SeriesDto.Post post);
    Series PatchDtoToSeries(SeriesDto.Patch patch);
    SeriesDto.Response SeriesToResponseDto(Series series);
    SeriesDto.SimpleResponse SeriesToSimpleResponseDto(Series series);
}
