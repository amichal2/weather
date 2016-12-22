package com.amichal2.weather.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.amichal2.weather.domain.City;
import com.amichal2.weather.repository.CityRepository;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void retrieveAllCitiesSuccess() {
        final Set<City> testSet = new HashSet<>();
        final City testCity = new City("cityId", "city name");
        testSet.add(testCity);
        given(cityRepository.getAllCities()).willReturn(testSet);

        assertEquals(testSet, cityService.retrieveAllCities());
    }
}
