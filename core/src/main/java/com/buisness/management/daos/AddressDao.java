package com.buisness.management.daos;

import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;
import com.buisness.management.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AddressDao {
    private final DatabaseAccess databaseAccess;

    public AddressDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    public void create(Address address){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_ADDRESS)) {
            fillCreateStatement(statement, address);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Address> findAll(){
        List<Address> addresses = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_ADDRESSES)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addresses.add(mapRowToAddress(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public Address findById(Integer id){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_ADDRESS_BY_ID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToAddress(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement, Address address) throws SQLException {
        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setInt(2, address.getNumber());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getPostalCode());
        return preparedStatement;
    }

    private Address mapRowToAddress(ResultSet resultSet) throws SQLException{
        return Address.builder()
                .id(resultSet.getInt("number"))
                .city(resultSet.getString("city"))
                .number(resultSet.getInt("number"))
                .postalCode(resultSet.getString("postal_code"))
                .street(resultSet.getString("street"))
                .build();
    }
}
