package com.buisness.management.daos;

import com.buisness.management.DataManager;
import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;
import com.buisness.management.model.Address;
import com.buisness.management.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDao {
    private final DatabaseAccess databaseAccess;
    private final DataManager dataManager;

    public EmployeeDao(DatabaseAccess databaseAccess, DataManager dataManager) {
        this.databaseAccess = databaseAccess;
        this.dataManager = dataManager;
    }

    public void create(Employee employee, Integer addressId){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_EMPLOYEE)) {

            fillCreateStatement(statement, employee, addressId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> findAll(){
        List<Employee> employees = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_EMPLOYEES)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(mapRowToEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement, Employee employee, Integer addressId) throws SQLException {
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, addressId);
        return preparedStatement;
    }

    private Employee mapRowToEmployee(ResultSet resultSet) throws SQLException{
        Address address = dataManager.getAddressDao().findById(resultSet.getInt("address_id"));
        return Employee.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .address(address)
                .build();
    }
}
