package com.amichal2.weather.integration.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {

    @JsonProperty("weather")
    private List<WeatherDescription> descriptions;

    @JsonProperty("main")
    private MainInformation mainInformation;

    @JsonProperty("sys")
    private SystemInformation systemInformation;

    @JsonProperty("name")
    private String cityName;

    public List<WeatherDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<WeatherDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public MainInformation getMainInformation() {
        return mainInformation;
    }

    public void setMainInformation(MainInformation mainInformation) {
        this.mainInformation = mainInformation;
    }

    public SystemInformation getSystemInformation() {
        return systemInformation;
    }

    public void setSystemInformation(SystemInformation systemInformation) {
        this.systemInformation = systemInformation;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
