package com.buisness.management.db;

public class DbQueries {
    private DbQueries() {
    }
    public static final String INSERT_ADDRESS = "insert into g21.address(street, number, city, postal_code) values(?, ?, ?, ?)";
    public static final String SELECT_ADDRESSES = "select * from g21.address;";
    public static final String SELECT_ADDRESS_BY_ID = "select * from g21.address where id = ?";

    public static final String INSERT_EMPLOYEE = "insert into g21.employees(first_name, last_name, address_id) values(?, ?, ?)";
    public static final String SELECT_EMPLOYEES = "select * from g21.employees;";
    public static final String SELECT_EMPLOYEE_BY_ID = "select * from g21.employees where id = ?";

    public static final String INSERT_CLIENT = "insert into g21.clients(first_name, last_name, address_id) values(?, ?, ?)";
    public static final String SELECT_CLIENTS = "select * from g21.clients;";
    public static final String SELECT_CLIENT_BY_ID = "select * from g21.clients where id = ?";

    public static final String INSERT_PRODUCT = "insert into g21.products(name, code, quantity, price) values(?, ?, ?, ?)";
    public static final String SELECT_PRODUCTS = "select * from g21.products;";
    public static final String SELECT_PRODUCT_BY_ID = "select * from g21.products where products.id = ?";
    public static final String SELECT_PRODUCTS_BY_IDS_IN = "select * from g21.products where products.id in";

    public static final String INSERT_ORDER = "insert into g21.orders(client_id, date) values(?, ?); ";
            //"set @last_insert_id = LAST_INSERT_ID();";
    public static final String INSERT_ORDER_PRODUCT = "insert into g21.order_product(order_id, product_id, quantity)" +
            //" values(@last_insert_id, ?, ?)";
            " values((select id from g21.orders order by id desc limit 1), ?, ?)";

    public static final String SELECT_ORDERS = "select * from g21.orders;";

    public static final String SELECT_PRODUCT_IDS = "select product_id, quantity from g21.order_product where order_id = ?;";

}
