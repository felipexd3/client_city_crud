package com.blank.ilia.service;

import com.blank.ilia.model.City;
import com.blank.ilia.model.dto.CityDTO;
import com.blank.ilia.model.enums.StateEnum;
import com.blank.ilia.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CityService {
    private final CityRepository cityRepository;

    public City saveCity(CityDTO cityDTO) {
        try {
            return this.cityRepository.save(City.builder()
                    .name(cityDTO.getName())
                    .state(cityDTO.getState()).build());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<City> fetchByNameOrState(String name, StateEnum stateEnum) {
        if (Objects.isNull(name) && Objects.isNull(stateEnum)) {
            throw new BadRequestException("Is must need specify one parameter.");
        } else {
            return Objects.nonNull(name) ? this.cityRepository.findAllByNameContainingIgnoreCase(name) : this.cityRepository.findAllByState(stateEnum);
        }
    }
}
