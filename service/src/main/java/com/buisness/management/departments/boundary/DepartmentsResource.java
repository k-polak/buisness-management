package com.buisness.management.departments.boundary;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/departments")
@RequestScoped
public class DepartmentsResource {

    @GET
    public void getAllDepartments(){
        System.out.println();
        return;
    }
}
