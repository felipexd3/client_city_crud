package com.blank.ilia.service;

import com.blank.ilia.IliaApplicationTests;
import com.blank.ilia.model.City;
import com.blank.ilia.model.dto.CityDTO;
import com.blank.ilia.model.enums.StateEnum;
import com.blank.ilia.utils.CityUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import java.util.List;

public class CityTests extends IliaApplicationTests {
    @Test
    public void should_create_city_success() {
        CityDTO cityDTO = CityUtils.mountCity(faker);
        City city = this.cityService.saveCity(cityDTO);
        Assert.assertEquals(city.getName(), cityDTO.getName());
    }

    @Test
    public void should_find_city_by_name() {
        City city = this.cityService.saveCity(CityUtils.mountCity(faker));
        List<City> cityList = this.cityService.fetchByNameOrState(city.getName(), null);
        Assert.assertFalse(cityList.isEmpty());
    }

    @Test
    public void shouldnt_find_city_by_name() {
        List<City> cityList = this.cityService.fetchByNameOrState(faker.address().cityName(), null);
        Assert.assertTrue(cityList.isEmpty());
    }

    @Test
    public void should_find_city_by_state() {
        City city = this.cityService.saveCity(CityUtils.mountCity(faker));
        List<City> cityList = this.cityService.fetchByNameOrState(null, city.getState());
        Assert.assertFalse(cityList.isEmpty());
    }

    @Test
    public void shouldnt_find_city_by_state() {
        List<City> cityList = this.cityService.fetchByNameOrState(null, StateEnum.RJ);
        Assert.assertTrue(cityList.isEmpty());
    }

    @Test
    public void should_return_exception_find_city() {
        Throwable exception = Assert.assertThrows(BadRequestException.class,
                () -> this.cityService.fetchByNameOrState(null, null));
        Assert.assertEquals(exception.getMessage(), "Is must need specify one parameter.");
    }
}
