package com.buisness.management.products.control;

import com.buisness.management.DataManager;
import com.buisness.management.DtoMapper;
import com.buisness.management.dtos.ProductDTO;
import com.buisness.management.model.Product;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class ProductsController {
    DataManager dataManager = new DataManager();

    public List<ProductDTO> getAllProducts(){
        return dataManager.getProductDao().findAll()
                .stream()
                .map(DtoMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public void createProduct(ProductDTO productDTO){
        Product product = Product.builder()
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .quantity(productDTO.getQuantity())
                .build();
        dataManager.getProductDao().create(product);
    }
}
