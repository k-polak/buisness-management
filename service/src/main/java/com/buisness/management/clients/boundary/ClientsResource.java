package com.buisness.management.clients.boundary;

import com.buisness.management.clients.control.ClientsController;
import com.buisness.management.dtos.ClientDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/clients")
@RequestScoped
public class ClientsResource {

    @Inject
    ClientsController clientsController;

    @GET
    public List<ClientDTO> getClients(){
        return clientsController.getAllClients();
    }
}
