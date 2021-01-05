package com.buisness.management.orders.boundary;

import com.buisness.management.dtos.ClientDTO;
import com.buisness.management.dtos.OrderDTO;
import com.buisness.management.orders.control.OrdersController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
@RequestScoped
public class OrdersResource {
    @Inject
    OrdersController ordersController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<OrderDTO> getAllOrders(){
        return ordersController.getAllOrders();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createOrder(OrderDTO orderDTO){
        ordersController.createOrder(orderDTO);
    }
}
