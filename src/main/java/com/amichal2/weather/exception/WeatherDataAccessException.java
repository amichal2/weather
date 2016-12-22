package com.amichal2.weather.exception;

//TODO split across client and server side errors
public class WeatherDataAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WeatherDataAccessException() {
    }
    
    public WeatherDataAccessException(String message) {
        super(message);
    }
}
