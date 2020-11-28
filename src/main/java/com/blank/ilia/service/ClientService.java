package com.blank.ilia.service;

import com.blank.ilia.exceptions.EntityNotFoundException;
import com.blank.ilia.model.City;
import com.blank.ilia.model.Client;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.repository.CityRepository;
import com.blank.ilia.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClientService {
    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;

    public Client saveClient(ClientDTO client) {
        try {
            City city = this.cityRepository.findByNameAndState(client.getCity().getName().toUpperCase(), client.getCity().getState())
                    .orElseGet(() -> this.cityRepository.save(City.builder()
                            .name(client.getCity().getName())
                            .state(client.getCity().getState())
                            .build()));
            return this.clientRepository.save(Client.builder()
                    .name(client.getName())
                    .birthdate(client.getBirthdate())
                    .gender(client.getGender())
                    .city(city).build());
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("One or more parameters are required");
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public List<Client> fetchClientByName(String name) {
        return this.clientRepository.findAllByNameContainingAndRemovedAtIsNull(name);
    }

    public Client fetchClientById(Long id) {
        return this.clientRepository.findByIdAndRemovedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(Client.class));
    }

    public Client editClient(Long id, ClientDTO clientDTO) {
        Client client = this.clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Client.class));
        client.setName(clientDTO.getName());
        return this.clientRepository.save(client);
    }
}
