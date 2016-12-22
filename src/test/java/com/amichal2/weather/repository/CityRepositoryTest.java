package com.amichal2.weather.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityRepositoryTest {

    private static final int REPO_SIZE = 2;

    private CityRepository cityRepository = new CityRepository();

    @Test
    public void cityRepositorySize() {
        assertEquals(REPO_SIZE, cityRepository.getAllCities().size());
    }
}
