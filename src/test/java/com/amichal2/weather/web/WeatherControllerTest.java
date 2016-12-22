package com.amichal2.weather.web;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.amichal2.weather.domain.WeatherInfo;
import com.amichal2.weather.exception.WeatherDataAccessException;
import com.amichal2.weather.service.CityService;
import com.amichal2.weather.service.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityServiceMock;

    @MockBean
    private WeatherService weatherServiceMock;

    @Test
    public void renderForm() throws Exception {
        mockMvc.perform(get("/weather"))
                .andExpect(content().string(containsString("Weather Service")))
                .andExpect(content().string(containsString("select city:")))
                .andExpect(view().name("weather"));
    }

    @Test
    public void submitForm() throws Exception {
        given(weatherServiceMock.provideWeatherInformation(anyString())).willReturn(new WeatherInfo());

        mockMvc.perform(post("/weather"))
                .andExpect(content().string(containsString("Weather Information")))
                .andExpect(view().name("result"));
    }

    @Test
    public void submitFormWeatherDataAccessException() throws Exception {
        given(weatherServiceMock.provideWeatherInformation(anyString())).willThrow(new WeatherDataAccessException());

        mockMvc.perform(post("/weather"))
        .andExpect(content().string(containsString("Error Page")))
        .andExpect(view().name("error"));
    }
}
