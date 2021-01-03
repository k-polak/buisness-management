package com.buisness.management.daos;

import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderProductDao {
    DatabaseAccess databaseAccess;

    public OrderProductDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    public SortedMap<Integer, Integer> getProductsForOrder(Integer orderId) {
        TreeMap<Integer, Integer> productIds = new TreeMap<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_PRODUCT_IDS)) {

            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                productIds.put(resultSet.getInt("product_id"), resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productIds;
    }
}
