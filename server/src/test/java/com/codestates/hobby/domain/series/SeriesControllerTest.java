package com.codestates.hobby.domain.series;

import com.codestates.hobby.domain.series.controller.SeriesController;
import com.codestates.hobby.domain.series.mapper.SeriesMapper;
import com.codestates.hobby.domain.series.service.SeriesService;
import com.codestates.hobby.utils.ControllerTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(value = SeriesController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class SeriesControllerTest extends ControllerTest {
    @MockBean
    SeriesService service;

    @MockBean
    SeriesMapper mapper;


    @Autowired
    private Gson gson;

    @Test
    public void post() {
        //given
        //when
        //then
    }

    @Test
    public void patch() {
        //given
        //when
        //then
    }

    @Test
    public void delete() {
        //given
        //when
        //then
    }

    @Test
    public void getAllByCategory() {
        //given
        //when
        //then
    }

    @Test
    public void getAllByMember() {
        //given
        //when
        //then
    }
}
