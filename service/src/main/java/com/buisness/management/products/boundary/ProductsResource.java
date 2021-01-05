package com.buisness.management.products.boundary;

import com.buisness.management.dtos.ProductDTO;
import com.buisness.management.products.control.ProductsController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductsResource {
    @Inject
    ProductsController productsController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ProductDTO> getAllProducts(){
        return productsController.getAllProducts();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createProduct(ProductDTO productDTO){
        productsController.createProduct(productDTO);
    }
}
