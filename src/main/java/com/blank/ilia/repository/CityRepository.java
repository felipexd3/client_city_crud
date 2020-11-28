package com.blank.ilia.repository;

import com.blank.ilia.model.City;
import com.blank.ilia.model.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameAndState(String name, StateEnum state);
    List<City> findAllByNameContainingIgnoreCase(String name);
    List<City> findAllByState(StateEnum stateEnum);
}
