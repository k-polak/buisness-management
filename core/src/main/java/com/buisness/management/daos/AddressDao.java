package com.buisness.management.daos;

import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDao {
    private DatabaseAccess databaseAccess;

    public AddressDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    public void create(Address address){
        try (Connection connection = databaseAccess.getConnection()) {
            String sql = "insert into g21.address(street, number, city, postal_code) values(?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            fillCreateStatement(statement, address);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement, Address address) throws SQLException {
        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setInt(2, address.getNumber());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getPostalCode());
        return preparedStatement;
    }
}
