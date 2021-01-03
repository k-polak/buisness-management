package com.buisness.management.daos;

import com.buisness.management.DataManager;
import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;
import com.buisness.management.model.Address;
import com.buisness.management.model.Client;
import com.buisness.management.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClientDao {
    private final DatabaseAccess databaseAccess;
    private final DataManager dataManager;

    public ClientDao(DatabaseAccess databaseAccess, DataManager dataManager) {
        this.databaseAccess = databaseAccess;
        this.dataManager = dataManager;
    }

    public void create(Client client, Integer addressId){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_CLIENT)) {

            fillCreateStatement(statement, client, addressId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> findAll(){
        List<Client> clients = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_CLIENTS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(mapRowToClient(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client findById(Integer id){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_CLIENT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToClient(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement,
                                                  Client client, Integer addressId) throws SQLException {
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setInt(3, addressId);
        return preparedStatement;
    }

    private Client mapRowToClient(ResultSet resultSet) throws SQLException{
        Address address = dataManager.getAddressDao().findById(resultSet.getInt("address_id"));
        return Client.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .address(address)
                .build();
    }
}
