package com.codestates.hobby.domain.series;

import com.codestates.hobby.domain.series.service.SeriesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SeriesServiceTest {
    //@Mock
    //private SeriesRepository repository;

    @InjectMocks
    private SeriesService service;

    @Nested
    @DisplayName("시리즈 생성")
    class create {
        void 시리즈를_생성한다() {
        }
        void 로그인한_회원만_생성_가능하다() {
        }
    }

    @Nested
    @DisplayName("시리즈 수정")
    class edit {
        void 시리즈를_수정한다() {
        }
        void 작성자만_수정_가능하다() {
        }
    }

    @Nested
    @DisplayName("시리즈 삭제")
    class delete {
        void 시리즈를_삭제한다() {
        }
        void 작성자만_삭제_가능하다() {
        }
    }

    @Nested
    @DisplayName("시리즈 조회")
    class find {
        void 카테고리에_해당하는_시리즈_목록을_조회한다() {
        }
        void 본인이_작성한_시리즈_목록을_조회한다() {
        }
        void 검색어로_시리즈를_조회한다() {
        }
    }
}
