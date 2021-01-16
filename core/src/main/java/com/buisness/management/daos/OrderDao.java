package com.buisness.management.daos;

import com.buisness.management.DataManager;
import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;
import com.buisness.management.model.Client;
import com.buisness.management.model.Order;
import com.buisness.management.model.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderDao {
    DatabaseAccess databaseAccess;
    DataManager dataManager;

    public OrderDao(DatabaseAccess databaseAccess, DataManager dataManager) {
        this.databaseAccess = databaseAccess;
        this.dataManager = dataManager;
    }
    public void create(Integer clientId, Map<Integer, Integer> products){
        try(Connection connection = databaseAccess.getConnection()) {
            connection.setAutoCommit(false);
            createOrderWithTransaction(connection, clientId, products);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> findAll(){
        List<Order> orders = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_ORDERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRowToOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement,  Integer clientId) throws SQLException {
        preparedStatement.setInt(1, clientId);
        preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
        return preparedStatement;
    }

    private void createOrderProduct(Connection connection, Integer productId, Integer quantity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_ORDER_PRODUCT);
        statement.setInt(1, productId);
        statement.setInt(2, quantity);
        statement.executeUpdate();
    }

    private void createOrderWithTransaction(Connection connection, Integer clientId, Map<Integer, Integer> products) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_ORDER)) {
            fillCreateStatement(statement, clientId);
            statement.executeUpdate();

            for(Map.Entry<Integer, Integer> entry : products.entrySet()){
                createOrderProduct(connection, entry.getKey(), entry.getValue());
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1690) {
                throw new RuntimeException("Not enough quantity");
            }
            connection.rollback();
            e.printStackTrace();
        }
    }

    private Order mapRowToOrder(ResultSet resultSet) throws SQLException {
        Integer clientId = resultSet.getInt("client_id");
        Integer orderId = resultSet.getInt("id");
        Client client = dataManager.getClientDao().findById(clientId);
        SortedMap<Integer, Integer> productsIdsMap = dataManager.getOrderProductDao().getProductsForOrder(orderId);
        List<Integer> productIds = new LinkedList<>(productsIdsMap.keySet());
        List<Product> products = dataManager.getProductDao().findByIds(productIds);

        Map<Product, Integer> productsWithQuantities = products
                .stream()
                .collect(Collectors.toMap(Function.identity(), product -> productsIdsMap.get(product.getId())));

        Float sum = (float) productsWithQuantities.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        return Order.builder()
                .id(orderId)
                .client(client)
                .products(productsWithQuantities)
                .sum(sum)
                .date(resultSet.getDate("date").toLocalDate())
                .build();

    }
}

