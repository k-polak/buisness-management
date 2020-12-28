package com.buisness.management.employees.control;

import com.buisness.management.DataManager;
import com.buisness.management.DtoMapper;
import com.buisness.management.dtos.CreateEmployeeDTO;
import com.buisness.management.dtos.EmployeeDTO;
import com.buisness.management.model.Employee;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class EmployeesController {
    DataManager dataManager = new DataManager();

    public List<EmployeeDTO> getAllEmployees(){
        return dataManager.getEmployeeDao()
                .findAll()
                .stream()
                .map(DtoMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(Integer id){
        return DtoMapper.mapToEmployeeDTO(dataManager.getEmployeeDao().findById(id));
    }

    public void createEmployee(CreateEmployeeDTO createEmployeeDTO){
        Employee employee = Employee.builder()
                .firstName(createEmployeeDTO.getFirstName())
                .lastName(createEmployeeDTO.getLastName())
                .build();
        dataManager.getEmployeeDao().create(employee, createEmployeeDTO.getAddressId());
    }
}
