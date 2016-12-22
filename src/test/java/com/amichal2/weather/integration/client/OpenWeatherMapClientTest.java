package com.amichal2.weather.integration.client;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.amichal2.weather.dto.WeatherResponseTestDTO;
import com.amichal2.weather.integration.dto.response.WeatherResponse;

public class OpenWeatherMapClientTest {

    private static final String TEST_URL = "TEST_URL";

    private static final String TEST_API_KEY = "TEST_API_KEY";

    private static final String TEST_CITY_ID = "TEST_CITY_ID";

    private OpenWeatherMapClient openWeatherMapClient;

    private MockRestServiceServer mockServer;

    private WeatherResponseTestDTO weatherResponseTestDTO;

    @Before
    public void setUp() throws Exception {
        openWeatherMapClient = new OpenWeatherMapClient();
        ReflectionTestUtils.setField(openWeatherMapClient, "url", TEST_URL);
        ReflectionTestUtils.setField(openWeatherMapClient, "apiKey", TEST_API_KEY);
        mockServer = MockRestServiceServer
                .createServer((RestTemplate) ReflectionTestUtils.getField(openWeatherMapClient, "restTemplate"));
        weatherResponseTestDTO = new WeatherResponseTestDTO();
    }

    @Test
    public void checkWeatherSuccess() throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("openWeatherTestResponse.json", getClass());
        mockServer.expect(requestTo(TEST_URL + "?id=" + TEST_CITY_ID + "&APPID=" + TEST_API_KEY))
                .andExpect(method(HttpMethod.GET)).andRespond(withSuccess(
                        classPathResource, MediaType.APPLICATION_JSON));

        final WeatherResponse actualResponse = openWeatherMapClient.checkWeather(TEST_CITY_ID);

        assertEquals(weatherResponseTestDTO.get().getCityName(), actualResponse.getCityName());
        assertEquals(weatherResponseTestDTO.get().getDescriptions().get(0).getDescription(),
                actualResponse.getDescriptions().get(0).getDescription());
        assertEquals(weatherResponseTestDTO.get().getMainInformation().getTemperature(),
                actualResponse.getMainInformation().getTemperature());
        assertEquals(weatherResponseTestDTO.get().getSystemInformation().getSunrise(),
                actualResponse.getSystemInformation().getSunrise());
        assertEquals(weatherResponseTestDTO.get().getSystemInformation().getSunset(),
                actualResponse.getSystemInformation().getSunset());
        mockServer.verify();
    }
}
