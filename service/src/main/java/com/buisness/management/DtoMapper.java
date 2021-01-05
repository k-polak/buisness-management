package com.buisness.management;

import com.buisness.management.daos.AddressDao;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
                .addressId(client.getAddress().getId())
                .build();
    }
    public static Client mapToClient(ClientDTO clientDTO){
        return Client.builder()
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .address(new DataManager().getAddressDao().findById(clientDTO.getAddressId()))
                .build();
    }

    public static Product mapToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .quantity(productDTO.getQuantity())
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

    public static Order mapToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .client(new DataManager().getClientDao().findById(orderDTO.getClient_id()))
                .date(orderDTO.getDate())
                .products(orderDTO
                        .getOrder_products()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> new DataManager().getProductDao().findById(entry.getKey()),
                                Map.Entry::getValue)))
                .build();
    }

    public static OrderDTO mapToOrderDTO(Order order){
        Map<Integer, Integer> order_products = order.getProducts().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getId(), Map.Entry::getValue
                ));
        return OrderDTO.builder()
                .id(order.getId())
                .client_id(order.getClient().getId())
                .order_products(order_products)
                .date(order.getDate())
                .build();
    }
}
