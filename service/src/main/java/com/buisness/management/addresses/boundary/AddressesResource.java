package com.buisness.management.addresses.boundary;

import com.buisness.management.addresses.control.AddressesController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/addresses")
@RequestScoped
public class AddressesResource {

    @Inject
    AddressesController addressesController;

    @GET
    public String getAddresses(){
        return addressesController.getFirstRow();
    }
}
