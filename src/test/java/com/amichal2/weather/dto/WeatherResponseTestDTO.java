package com.amichal2.weather.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.amichal2.weather.integration.dto.response.MainInformation;
import com.amichal2.weather.integration.dto.response.SystemInformation;
import com.amichal2.weather.integration.dto.response.WeatherDescription;
import com.amichal2.weather.integration.dto.response.WeatherResponse;

/**
 * Any changes of constant values in this class should be reflected in openWeatherTestResponse.json file as well
 *
 */
public class WeatherResponseTestDTO {

    private static final String WEATHER_DESC = "WEATHER_DESC";

    private static final BigDecimal TEMPERATURE = BigDecimal.TEN;

    private static final LocalTime SUNRISE = LocalTime.NOON;

    private static final LocalTime SUNSET = LocalTime.MIDNIGHT;

    private static final String TEST_CITY_NAME = "TEST_CITY_NAME";

    private WeatherResponse weatherResponse;

    public WeatherResponseTestDTO() {
        weatherResponse = new WeatherResponse();

        WeatherDescription weatherDescription = new WeatherDescription();
        weatherDescription.setDescription(WEATHER_DESC);
        List<WeatherDescription> descriptions = new ArrayList<>();
        descriptions.add(weatherDescription);
        weatherResponse.setDescriptions(descriptions);

        MainInformation mainInformation = new MainInformation();
        mainInformation.setTemperature(TEMPERATURE);
        weatherResponse.setMainInformation(mainInformation);

        SystemInformation systemInformation = new SystemInformation();
        systemInformation.setSunrise(SUNRISE);
        systemInformation.setSunset(SUNSET);
        weatherResponse.setSystemInformation(systemInformation);

        weatherResponse.setCityName(TEST_CITY_NAME);
    }

    public WeatherResponse get() {
        return weatherResponse;
    }

    public void setupNullTemperature() {
        weatherResponse.getMainInformation().setTemperature(null);
    }
}
