package com.buisness.management.addresses.control;

import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Dependent
public class AddressesController {

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
}
