package com.amichal2.weather.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amichal2.weather.domain.City;
import com.amichal2.weather.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Set<City> retrieveAllCities() {
        return cityRepository.getAllCities();
    }
}
