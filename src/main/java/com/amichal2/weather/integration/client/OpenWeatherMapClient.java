package com.amichal2.weather.integration.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.amichal2.weather.exception.WeatherDataAccessException;
import com.amichal2.weather.integration.dto.response.WeatherResponse;

@Service
public class OpenWeatherMapClient {

    private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    private static final String CITY_ID = "id";

    private static final String APPLICATION_ID = "APPID";

    @Value("${openweathermap.url}")
    private String url;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * @throws WeatherDataAccessException in case of connectivity issues with the weather data provider
     *  
     */
    public WeatherResponse checkWeather(String cityId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam(CITY_ID, cityId)
                .queryParam(APPLICATION_ID, apiKey);
        WeatherResponse weatherResponse = null;
        try {
            weatherResponse = restTemplate
                    .getForObject(builder.build().toUri(), WeatherResponse.class);
        } catch (RestClientException e) {
            //TODO manage exception based on type (client side or server side) and exact cause
            final String message = "Exception during OpenWeatherMap web service call";
            LOG.error(message, e);
            throw new WeatherDataAccessException(message);
        }

        return weatherResponse;
    }
}
