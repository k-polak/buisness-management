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

    public ProductDTO getProductById(int id) {
        return DtoMapper.mapToProductDTO(dataManager.getProductDao().findById(id));
    }

    public void createProduct(ProductDTO productDTO){
        dataManager.getProductDao().create(DtoMapper.mapToProduct(productDTO));
    }
}
