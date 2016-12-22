package com.amichal2.weather.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.amichal2.weather.domain.City;

@Repository
public class CityRepository {

    //TODO To be moved to persistent storage
    private static final Set<City> CITIES;

    static {
        Set<City> availableCities = new HashSet<>();
        availableCities.add(new City("2643743", "London"));
        availableCities.add(new City("1819729", "Hong Kong"));
        CITIES = Collections.unmodifiableSet(availableCities);
    }

    public Set<City> getAllCities() {
        return CITIES;
    }
}
