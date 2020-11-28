package com.blank.ilia.service;

import com.blank.ilia.IliaApplicationTests;
import com.blank.ilia.exceptions.EntityNotFoundException;
import com.blank.ilia.model.Client;
import com.blank.ilia.model.dto.ClientDTO;
import com.blank.ilia.utils.ClientUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import java.util.List;

public class ClientTests extends IliaApplicationTests {
    @Test
    public void should_create_client() {
        ClientDTO clientDTO = ClientUtils.mountClient(faker, false);
        Client client = this.clientService.saveClient(clientDTO);
        Assert.assertEquals(client.getName(), clientDTO.getName());
    }

    @Test
    public void should_create_client_failure() {
        ClientDTO clientDTO = ClientUtils.mountClient(faker, true);
        Throwable exception = Assert.assertThrows(BadRequestException.class,
                () -> this.clientService.saveClient(clientDTO));
        Assert.assertEquals(exception.getMessage(), "One or more parameters are required");
    }

    @Test
    public void should_find_client_by_name() {
        Client client = this.clientService.saveClient(ClientUtils.mountClient(faker, false));
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
        Client client = this.clientService.saveClient(ClientUtils.mountClient(faker, false));
        Client clientSearched = this.clientService.fetchClientById(client.getId());
        Assert.assertEquals(client.getId(), clientSearched.getId());
    }

    @Test
    public void should_return_exception_find_client() {
        Throwable exception = Assert.assertThrows(EntityNotFoundException.class,
                () -> this.clientService.fetchClientById(1000L));
        Assert.assertEquals(exception.getMessage(), "Cannot find ".concat(Client.class.getSimpleName().toUpperCase()));
    }

    @Test
    public void should_edit_client_success() {
        ClientDTO clientDTO = ClientUtils.mountClient(faker, false);
        Client client = this.clientService.saveClient(clientDTO);
        clientDTO.setName(faker.gameOfThrones().character());
        client = this.clientService.editClient(client.getId(), clientDTO);
        Assert.assertEquals(client.getName(), clientDTO.getName());
    }

    @Test
    public void should_edit_client_failure() {
        Throwable exception = Assert.assertThrows(EntityNotFoundException.class,
                () -> this.clientService.editClient(1000L, ClientUtils.mountClient(faker, false)));
        Assert.assertEquals(exception.getMessage(), "Cannot find ".concat(Client.class.getSimpleName().toUpperCase()));
    }
}
