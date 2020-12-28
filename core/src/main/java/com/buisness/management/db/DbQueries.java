package com.buisness.management.db;

public class DbQueries {
    private DbQueries() {
    }
    public static final String INSERT_ADDRESS = "insert into g21.address(street, number, city, postal_code) values(?, ?, ?, ?)";
    public static final String SELECT_ADDRESSES = "select * from g21.address;";
    public static final String SELECT_ADDRESS_BY_ID = "select * from g21.address where id = ?";

    public static final String INSERT_EMPLOYEE = "insert into g21.employees(first_name, last_name, address_id) values(?, ?, ?)";
    public static final String SELECT_EMPLOYEES = "select * from g21.employees;";
}
