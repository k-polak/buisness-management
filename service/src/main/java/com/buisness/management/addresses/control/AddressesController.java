package com.buisness.management.addresses.control;

import com.buisness.management.DataManager;
import com.buisness.management.model.Address;

import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Dependent
public class AddressesController {
    DataManager dataManager = new DataManager();

    public String getFirstRow() {
        ResultSet rs = null;
        String toReturn = "empty to return";
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/TestDS");
            Connection conn = ds.getConnection();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("select * from address");
            rs.next();
            toReturn = rs.getString("street");
        } catch (Exception e) {
            System.out.println("So sad found error: " + e.getLocalizedMessage());
        }
        return  toReturn;
    }

    public void createAddress(){
        Address address = Address.builder()
                .street("krakowska")
                .number(123)
                .city("Warszawa")
                .postalCode("22-332")
                .build();
        dataManager.getAddressDao().create(address);
    }
}
