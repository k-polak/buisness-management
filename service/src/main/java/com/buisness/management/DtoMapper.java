package com.buisness.management;

import com.buisness.management.dtos.AddressDTO;
import com.buisness.management.dtos.EmployeeDTO;
import com.buisness.management.model.Address;
import com.buisness.management.model.Employee;

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
}
