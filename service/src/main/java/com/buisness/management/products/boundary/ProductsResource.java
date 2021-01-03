package com.buisness.management.products.boundary;

import com.buisness.management.dtos.ProductDTO;
import com.buisness.management.products.control.ProductsController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/products")
public class ProductsResource {
    @Inject
    ProductsController productsController;

    @GET
    public List<ProductDTO> getAllProducts(){
        return productsController.getAllProducts();
    }

    @POST
    public void createProduct(ProductDTO productDTO){
        productsController.createProduct(productDTO);
    }
}
