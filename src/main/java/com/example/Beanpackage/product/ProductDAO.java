package com.example.Beanpackage.product;

import com.example.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String INSERT_PRODUCT = "INSERT INTO products (user_id, category_id, pname, price, description, quantity, pimage) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT user_id, category_id, pname, price, description, quantity, pimage FROM products WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM products WHERE id = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE products SET user_id = ?, category_id = ?, pname = ?, price = ?, description = ?, quantity = ?, pimage = ? WHERE id = ?";

    public void insertProduct(Connection connection, ProductBeanCls product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setInt(1, product.getUser_id());
            preparedStatement.setInt(2, product.getCategory_id());
            preparedStatement.setString(3, product.getPname());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getQuantity());
            preparedStatement.setString(7, product.getPimage());

            preparedStatement.executeUpdate();
        }
    }

    public ProductBeanCls selectProduct(int id) {
        ProductBeanCls product = null;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int category_id = rs.getInt("category_id");
                String pname = rs.getString("pname");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String pimage = rs.getString("pimage");
                product = new ProductBeanCls();
                product.setUser_id(user_id);
                product.setCategory_id(category_id);
                product.setPname(pname);
                product.setPrice(price);
                product.setDescription(description);
                product.setQuantity(quantity);
                product.setPimage(pimage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<ProductBeanCls> selectAllProducts() {
        List<ProductBeanCls> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                int category_id = rs.getInt("category_id");
                String pname = rs.getString("pname");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String pimage = rs.getString("pimage");
                ProductBeanCls product = new ProductBeanCls();
                product.setUser_id(user_id);
                product.setCategory_id(category_id);
                product.setPname(pname);
                product.setPrice(price);
                product.setDescription(description);
                product.setQuantity(quantity);
                product.setPimage(pimage);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduct(ProductBeanCls product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setInt(1, product.getUser_id());
            statement.setInt(2, product.getCategory_id());
            statement.setString(3, product.getPname());
            statement.setDouble(4, product.getPrice());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getPimage());
            statement.setInt(8, product.getId()); // Assuming ProductBeanCls has an 'id' field for primary key
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
