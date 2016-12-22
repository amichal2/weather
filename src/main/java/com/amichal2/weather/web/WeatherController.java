package com.amichal2.weather.web;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.amichal2.weather.domain.City;
import com.amichal2.weather.exception.WeatherDataAccessException;
import com.amichal2.weather.service.CityService;
import com.amichal2.weather.service.WeatherService;

@Controller("/weather")
public class WeatherController {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String weatherForm(Model model) {
        model.addAttribute("city", new City());
        return "weather";
    }

    @PostMapping
    public String weatherSearch(@ModelAttribute City city, Model model) {
        model.addAttribute("weatherInfo", weatherService.provideWeatherInformation(city.getId()));
        return "result";
    }

    @ModelAttribute("allCities")
    public Set<City> populateCities() {
        return cityService.retrieveAllCities();
    }

    @ExceptionHandler(WeatherDataAccessException.class)
    public String handleWeatherDataAccessException(WeatherDataAccessException e) {
        LOG.error("weather data access exception", e);
        return "error";
    }

    //TODO separate view
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e) {
        LOG.error("general exception", e);
        return "error";
    }
}
