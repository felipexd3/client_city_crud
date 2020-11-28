package com.blank.ilia.controller;

import com.blank.ilia.exceptions.EntityNotFoundException;
import com.blank.ilia.model.Client;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.service.ClientService;
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
@RequestMapping("client")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientController {
    private final ClientService clientService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Um ou mais parametros não foram satisfeitos"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        try {

            return new ResponseEntity(this.clientService.saveClient(clientDTO), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Client>> findAllByName(@RequestParam("name") String name) {
        try {
            return new ResponseEntity(this.clientService.fetchClientByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um cliente"),
            @ApiResponse(code = 204, message = "Cliente não encontrado"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @GetMapping("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Client> findClientById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity(this.clientService.fetchClientById(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
            @ApiResponse(code = 500, message = "Problema no servidor"),
    })
    @PutMapping("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity editClient(@PathVariable("id") Long id, @RequestBody @Valid ClientDTO clientDTO) {
        try {
            return new ResponseEntity(this.clientService.editClient(id, clientDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
