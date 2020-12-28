package com.buisness.management.addresses.boundary;

import com.buisness.management.addresses.control.AddressesController;
import com.buisness.management.dtos.AddressDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/addresses")
@RequestScoped
public class AddressesResource {

    @Inject
    AddressesController addressesController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<AddressDTO> getAddresses(){
        return addressesController.getAllAddresses();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createAddress(AddressDTO addressDTO){
        addressesController.createAddress(addressDTO);
    }
}
