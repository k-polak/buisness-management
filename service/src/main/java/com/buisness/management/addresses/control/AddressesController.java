package com.buisness.management.addresses.control;

import com.buisness.management.DataManager;
import com.buisness.management.DtoMapper;
import com.buisness.management.dtos.AddressDTO;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class AddressesController {
    DataManager dataManager = new DataManager();

    public List<AddressDTO> getAllAddresses() {
        return dataManager.getAddressDao().findAll()
                .stream()
                .map(DtoMapper::mapToAddressDTO)
                .collect(Collectors.toList());
    }

    public void createAddress(AddressDTO addressDTO){
        dataManager.getAddressDao().create(DtoMapper.mapToAddress(addressDTO));
    }
}