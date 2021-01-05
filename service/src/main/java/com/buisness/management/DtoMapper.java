package com.buisness.management;

import com.buisness.management.dtos.AddressDTO;
import com.buisness.management.dtos.ClientDTO;
import com.buisness.management.dtos.EmployeeDTO;
import com.buisness.management.dtos.OrderDTO;
import com.buisness.management.dtos.OrderProductDTO;
import com.buisness.management.dtos.ProductDTO;
import com.buisness.management.model.Address;
import com.buisness.management.model.Client;
import com.buisness.management.model.Employee;
import com.buisness.management.model.Order;
import com.buisness.management.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {
    public static AddressDTO mapToAddressDTO(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .city(address.getCity())
                .number(address.getNumber())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }

    public static Address mapToAddress(AddressDTO addressDTO){
        return Address.builder()
                .city(addressDTO.getCity())
                .number(addressDTO.getNumber())
                .postalCode(addressDTO.getPostalCode())
                .street(addressDTO.getStreet())
                .build();
    }

    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .address(DtoMapper.mapToAddressDTO(employee.getAddress()))
                .build();
    }

    public static ClientDTO mapToClientDTO(Client client){
        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(mapToAddressDTO(client.getAddress()))
                .build();
    }

    public static ProductDTO mapToProductDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .quantity(product.getQuantity())
                .build();
    }

    public static OrderDTO mapToOrderDTO(Order order){
        List<OrderProductDTO> orderProductDTOS = order.getProducts().entrySet()
                .stream()
                .map(entry ->{
                    return OrderProductDTO.builder()
                            .product(mapToProductDTO(entry.getKey()))
                            .amount(entry.getValue())
                            .build();
                })
                .collect(Collectors.toList());
        return OrderDTO.builder()
                .id(order.getId())
                .client(mapToClientDTO(order.getClient()))
                .products(orderProductDTOS)
                .date(order.getDate())
                .build();
    }
}
