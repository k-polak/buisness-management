package com.buisness.management.clients.boundary;

import com.buisness.management.clients.control.ClientsController;
import com.buisness.management.dtos.AddressDTO;
import com.buisness.management.dtos.ClientDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/clients")
@RequestScoped
public class ClientsResource {

    @Inject
    ClientsController clientsController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ClientDTO> getClients(){
        return clientsController.getAllClients();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createClient(ClientDTO clientDTO){
        clientsController.createClient(clientDTO);
    }
}
