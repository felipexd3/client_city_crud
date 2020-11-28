package com.blank.ilia.controller;

import com.blank.ilia.model.dto.CityDTO;
import com.blank.ilia.model.enums.StateEnum;
import com.blank.ilia.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity saveCity(@RequestBody @Valid CityDTO cityDTO) {
        try {
            return new ResponseEntity(this.cityService.saveCity(cityDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity fetchByNameOrState(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "state", required = false) StateEnum stateEnum ) {
        try{
            return new ResponseEntity(this.cityService.fetchByNameOrState(name, stateEnum), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
