package com.buisness.management.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDatabaseAccess implements DatabaseAccess {
    @Override
    public Connection getConnection() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/TestDS");
            return ds.getConnection();
        } catch (SQLException | NamingException e ){
            e.printStackTrace();
        }
        return null;
    }
}
