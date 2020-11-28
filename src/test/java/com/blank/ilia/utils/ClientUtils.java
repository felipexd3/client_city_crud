package com.blank.ilia.utils;

import com.blank.ilia.model.City;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.model.enums.GenderEnum;
import com.blank.ilia.model.enums.StateEnum;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientUtils {
    public static ClientDTO mountClient(Faker faker) {
        return ClientDTO.builder()
                .name(faker.gameOfThrones().character())
                .birthdate(LocalDate.now())
                .city(City.builder()
                        .name(faker.address().cityName())
                        .state(StateEnum.PE).build())
                .gender(GenderEnum.M)
                .build();
    }
}
