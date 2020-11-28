package com.blank.ilia.controller;

import com.blank.ilia.model.City;
import com.blank.ilia.model.dto.CityDTO;
import com.blank.ilia.model.enums.StateEnum;
import com.blank.ilia.service.CityService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CityController {
    private final CityService cityService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity saveCity(@RequestBody @Valid CityDTO cityDTO) {
        try {
            return new ResponseEntity(this.cityService.saveCity(cityDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de cidades"),
            @ApiResponse(code = 400, message = "Um ou mais parametros não foram satisfeitos"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<City>> fetchByNameOrState(@RequestParam(value = "name", required = false) String name,
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
