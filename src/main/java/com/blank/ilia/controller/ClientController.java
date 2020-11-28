package com.blank.ilia.controller;

import com.blank.ilia.exceptions.EntityNotFoundException;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        try {
            return new ResponseEntity(this.clientService.saveClient(clientDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity findAllByName(@RequestParam("name") String name) {
        try {
            return new ResponseEntity(this.clientService.fetchClientByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findClientById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity(this.clientService.fetchClientById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
