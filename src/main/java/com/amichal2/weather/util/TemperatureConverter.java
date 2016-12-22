package com.amichal2.weather.util;

import java.math.BigDecimal;
import java.util.Objects;

public class TemperatureConverter {

    //Suppresses default constructor, ensuring non-instantiability
    private TemperatureConverter() {
        throw new AssertionError();
    }

    public static BigDecimal kelvinToCelsius(BigDecimal kelvin) {
        Objects.requireNonNull(kelvin);
        return kelvin.subtract(new BigDecimal("273.15"));
    }

    public static BigDecimal kelvinToFahrenheit(BigDecimal kelvin) {
        return new BigDecimal("1.8").multiply(kelvinToCelsius(kelvin)).add(new BigDecimal("32"));
    }
}
