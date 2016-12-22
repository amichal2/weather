package com.amichal2.weather.integration.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescription {

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
