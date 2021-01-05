package com.buisness.management.clients.control;

import com.buisness.management.DataManager;
import com.buisness.management.DtoMapper;
import com.buisness.management.dtos.AddressDTO;
import com.buisness.management.dtos.ClientDTO;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class ClientsController {
    DataManager dataManager = new DataManager();

    public List<ClientDTO> getAllClients(){
        return dataManager.getClientDao().findAll()
                .stream()
                .map(DtoMapper::mapToClientDTO)
                .collect(Collectors.toList());
    }

    public void createClient(ClientDTO clientDTO){
        dataManager.getClientDao().create(DtoMapper.mapToClient(clientDTO));
    }
}
