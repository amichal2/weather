package com.amichal2.weather.util;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TemperatureConverterTest {

    private BigDecimal kelvin;

    private BigDecimal celsius;

    private BigDecimal fahrenheit;

    public TemperatureConverterTest(BigDecimal kelvin, BigDecimal celsius, BigDecimal fahrenheit) {
        this.kelvin = kelvin;
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }

    @Parameters
    public static Collection<BigDecimal[]> temperatures() {
        return Arrays.asList(new BigDecimal[][] {
                {BigDecimal.ZERO, new BigDecimal("-273.15"), new BigDecimal("-459.67")}, 
                {new BigDecimal("273.15"), BigDecimal.ZERO, new BigDecimal("32")},
                {new BigDecimal("274.15"), BigDecimal.ONE, new BigDecimal("33.8")},
                {new BigDecimal("283.15"), BigDecimal.TEN, new BigDecimal("50")}
            });
    }

    @Test
    public void kelvinToCelsiusTest() {
        assertTrue(celsius.compareTo(TemperatureConverter.kelvinToCelsius(kelvin)) == 0);
    }

    @Test
    public void kelvinToFahrenheitTest() {
        assertTrue(fahrenheit.compareTo(TemperatureConverter.kelvinToFahrenheit(kelvin)) == 0);
    }
}
