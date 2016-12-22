package com.amichal2.weather.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amichal2.weather.domain.WeatherInfo;
import com.amichal2.weather.dto.WeatherResponseTestDTO;
import com.amichal2.weather.exception.WeatherDataAccessException;
import com.amichal2.weather.integration.client.OpenWeatherMapClient;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    private static final String TEST_CITY_ID = "TEST_CITY_ID";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private OpenWeatherMapClient openWeatherMapClient;

    @InjectMocks
    private WeatherService weatherService;

    private WeatherResponseTestDTO weatherResponseTestDTO;

    @Before
    public void setUp() {
        weatherResponseTestDTO = new WeatherResponseTestDTO();
    }

    @Test
    public void provideWeatherInformationSuccess() {
        given(openWeatherMapClient.checkWeather(TEST_CITY_ID)).willReturn(weatherResponseTestDTO.get());

        final WeatherInfo weatherInfo = weatherService.provideWeatherInformation(TEST_CITY_ID);

        assertEquals(weatherResponseTestDTO.get().getCityName(), weatherInfo.getCityName());
        assertEquals(weatherResponseTestDTO.get().getDescriptions().get(0).getDescription(),
                weatherInfo.getWeatherDescription());
    }

    @Test
    public void provideWeatherInformationWeatherDataAccessException() {
        given(openWeatherMapClient.checkWeather(TEST_CITY_ID)).willThrow(new WeatherDataAccessException());
        expectedException.expect(WeatherDataAccessException.class);

        weatherService.provideWeatherInformation(TEST_CITY_ID);
    }

    @Test
    public void provideWeatherInformationNullTemperature() {
        weatherResponseTestDTO.setupNullTemperature();
        given(openWeatherMapClient.checkWeather(TEST_CITY_ID)).willReturn(weatherResponseTestDTO.get());
        expectedException.expect(NullPointerException.class);

        weatherService.provideWeatherInformation(TEST_CITY_ID);
    }

    //TODO more unit tests with other missing/invalid properties
}
