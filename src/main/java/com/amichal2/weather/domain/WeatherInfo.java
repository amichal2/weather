package com.amichal2.weather.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WeatherInfo {

    private LocalDate date;

    private String cityName;

    private String weatherDescription;

    private BigDecimal tempFahrenheit;

    private BigDecimal tempCelsius;

    private String sunrise;

    private String sunset;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public BigDecimal getTempFahrenheit() {
        return tempFahrenheit;
    }

    public void setTempFahrenheit(BigDecimal tempFahrenheit) {
        this.tempFahrenheit = tempFahrenheit;
    }

    public BigDecimal getTempCelsius() {
        return tempCelsius;
    }

    public void setTempCelsius(BigDecimal tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
