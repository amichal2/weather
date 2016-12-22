package com.amichal2.weather.service;

import static com.amichal2.weather.util.TemperatureConverter.kelvinToCelsius;
import static com.amichal2.weather.util.TemperatureConverter.kelvinToFahrenheit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amichal2.weather.domain.WeatherInfo;
import com.amichal2.weather.exception.WeatherDataAccessException;
import com.amichal2.weather.integration.client.OpenWeatherMapClient;
import com.amichal2.weather.integration.dto.response.WeatherResponse;

//TODO extract data formatting functionality into separate class
@Service
public class WeatherService {

    private static final int TEMPERATURE_SCALE = 1;

    private static final DateTimeFormatter AM_PM_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    /**
     * @throws WeatherDataAccessException in case of connectivity issues in downstream service
     *  
     */
    public WeatherInfo provideWeatherInformation(String cityId) {
        final WeatherResponse weatherResponse = openWeatherMapClient.checkWeather(cityId);
        WeatherInfo weatherInfo = new WeatherInfo();

        //TODO simplify bean mapping with lib e.g.: dozer
        weatherInfo.setDate(LocalDate.now());
        weatherInfo.setCityName(weatherResponse.getCityName());
        if (weatherResponse.getDescriptions() != null && !weatherResponse.getDescriptions().isEmpty()) {
            weatherInfo.setWeatherDescription(
                    StringUtils.capitalize(weatherResponse.getDescriptions().get(0).getDescription()));
        }

        if (weatherResponse.getMainInformation() != null) {
            final BigDecimal temperatureKelvin = weatherResponse.getMainInformation().getTemperature();
            weatherInfo.setTempFahrenheit(formatTemperature(kelvinToFahrenheit(temperatureKelvin)));
            weatherInfo.setTempCelsius(formatTemperature(kelvinToCelsius(temperatureKelvin)));
        }

        if (weatherResponse.getSystemInformation() != null) {
            weatherInfo.setSunrise(formatTime(weatherResponse.getSystemInformation().getSunrise()));
            weatherInfo.setSunset(formatTime(weatherResponse.getSystemInformation().getSunset()));
        }

        return weatherInfo;
    }

    private BigDecimal formatTemperature(BigDecimal temperature) {
        Objects.requireNonNull(temperature);
        return temperature.setScale(TEMPERATURE_SCALE, RoundingMode.HALF_UP);
    }

    private String formatTime(LocalTime localTime) {
        Objects.requireNonNull(localTime);
        return localTime.format(AM_PM_TIME_FORMAT).toLowerCase();
    }
}
