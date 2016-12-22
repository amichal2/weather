package com.amichal2.weather.integration.dto.response;

import java.time.LocalTime;

import com.amichal2.weather.integration.util.CustomLocalTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SystemInformation {

    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonProperty("sunrise")
    private LocalTime sunrise;

    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonProperty("sunset")
    private LocalTime sunset;

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }
}
