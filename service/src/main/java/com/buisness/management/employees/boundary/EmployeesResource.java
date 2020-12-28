package com.buisness.management.employees.boundary;

import com.buisness.management.dtos.CreateEmployeeDTO;
import com.buisness.management.dtos.EmployeeDTO;
import com.buisness.management.employees.control.EmployeesController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/employees")
@RequestScoped
public class EmployeesResource {

    @Inject
    EmployeesController employeesController;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<EmployeeDTO> getAllEmployees(){
        return employeesController.getAllEmployees();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public EmployeeDTO getEmployee(@PathParam("id") Integer id){
        return employeesController.getEmployee(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/create")
    public void createEmployee(CreateEmployeeDTO createEmployeeDTO){
        employeesController.createEmployee(createEmployeeDTO);
    }


}
