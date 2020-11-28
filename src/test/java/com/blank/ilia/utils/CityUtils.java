package com.blank.ilia.utils;

import com.blank.ilia.model.dto.CityDTO;
import com.blank.ilia.model.enums.StateEnum;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class CityUtils {

    public static CityDTO mountCity(Faker faker) {
        return CityDTO.builder().name(faker.address().cityName()).state(StateEnum.PE).build();
    }
}
