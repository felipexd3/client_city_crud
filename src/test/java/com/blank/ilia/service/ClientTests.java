package com.blank.ilia.service;

import com.blank.ilia.IliaApplicationTests;
import com.blank.ilia.exceptions.EntityNotFoundException;
import com.blank.ilia.model.Client;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.utils.ClientUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClientTests extends IliaApplicationTests {
    @Test
    public void should_create_client() {
        ClientDTO clientDTO = ClientUtils.mountClient(faker);
        Client client = this.clientService.saveClient(clientDTO);
        Assert.assertEquals(client.getName(), clientDTO.getName());
    }

    @Test
    public void should_find_client_by_name() {
        Client client = this.clientService.saveClient(ClientUtils.mountClient(faker));
        List<Client> clients = this.clientService.fetchClientByName(client.getName());
        Assert.assertFalse(clients.isEmpty());
    }

    @Test
    public void shouldnt_find_client_by_name() {
        List<Client> clients = this.clientService.fetchClientByName(faker.gameOfThrones().character());
        Assert.assertTrue(clients.isEmpty());
    }

    @Test
    public void should_find_client_by_id() {
        Client client = this.clientService.saveClient(ClientUtils.mountClient(faker));
        Client clientSearched = this.clientService.fetchClientById(client.getId());
        Assert.assertEquals(client.getId(), clientSearched.getId());
    }

    @Test
    public void should_return_exception_find_client() {
        Throwable exception = Assert.assertThrows(EntityNotFoundException.class,
                () -> this.clientService.fetchClientById(1000L));
        Assert.assertEquals(exception.getMessage(), "Cannot find ".concat(Client.class.getSimpleName().toUpperCase()));
    }
}
