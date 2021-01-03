package com.buisness.management.daos;

import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.DbQueries;
import com.buisness.management.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDao {
    DatabaseAccess databaseAccess;

    public ProductDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }

    public void create(Product product){
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.INSERT_PRODUCT)) {

            fillCreateStatement(statement, product);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll(){
        List<Product> products = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(DbQueries.SELECT_PRODUCTS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(mapRowToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> findByIds(List<Integer> ids){
        List<Product> products = new LinkedList<>();
        try (Connection connection = databaseAccess.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     createPreparedQueryWithInOperator(DbQueries.SELECT_PRODUCTS_BY_IDS_IN, ids.size()))) {

            fillSelectInStatement(statement, ids);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(mapRowToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private PreparedStatement fillCreateStatement(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCode());
        preparedStatement.setInt(3, product.getQuantity());
        return preparedStatement;
    }

    private PreparedStatement fillSelectInStatement(PreparedStatement preparedStatement, List<Integer> ids) throws SQLException {
            for(int i = 0; i< ids.size(); i++){
                preparedStatement.setInt(i+1, ids.get(i));
            }
            return preparedStatement;
    }

    private Product mapRowToProduct(ResultSet resultSet) throws SQLException{
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .code(resultSet.getString("code"))
                .quantity(resultSet.getInt("quantity"))
                .build();
    }

    private String createPreparedQueryWithInOperator(String query, int numberOfElements){
        StringBuilder sb = new StringBuilder(query);
        sb.append(" (");

        for(int i = 0; i< numberOfElements; i++){
            sb.append("?,");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        return sb.toString();
    }
}
