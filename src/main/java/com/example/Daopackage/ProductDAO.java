package com.example.Daopackage;

import com.example.Beanpackage.ProductBeanCls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void insertProduct(Connection connection, ProductBeanCls product) throws SQLException {
        String sql = "INSERT INTO products (user_id, category_id, pname, price, description, quantity, pimages) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getUser_id());
            preparedStatement.setInt(2, product.getCategory_id());
            preparedStatement.setString(3, product.getPname());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getQuantity());
            preparedStatement.setString(7, product.getPimages());
            preparedStatement.executeUpdate();
        }
    }

    public List<ProductBeanCls> getAllProducts(Connection connection) throws SQLException {
        List<ProductBeanCls> products = new ArrayList<>();
        String sql = "SELECT p.*, c.category AS category_name FROM products p JOIN category c ON p.category_id = c.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                ProductBeanCls product = new ProductBeanCls();
                product.setId(resultSet.getInt("id"));
                product.setUser_id(resultSet.getInt("user_id"));
                product.setCategory_id(resultSet.getInt("category_id"));
                product.setPname(resultSet.getString("pname"));
                product.setPrice(resultSet.getDouble("price"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPimages(resultSet.getString("pimages"));
                product.setCategoryName(resultSet.getString("category_name"));
                products.add(product);
            }
        }
        return products;
    }

    public ProductBeanCls getProductById(Connection connection, int id) throws SQLException {
        ProductBeanCls product = null;
        String sql = "SELECT p.*, c.category AS category_name FROM products p JOIN category c ON p.category_id = c.id WHERE p.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new ProductBeanCls();
                    product.setId(resultSet.getInt("id"));
                    product.setUser_id(resultSet.getInt("user_id"));
                    product.setCategory_id(resultSet.getInt("category_id"));
                    product.setPname(resultSet.getString("pname"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setPimages(resultSet.getString("pimages"));
                    product.setCategoryName(resultSet.getString("category_name"));
                }
            }
        }
        return product;
    }

    public List<ProductBeanCls> getProductsByUserId(Connection connection, int userId) throws SQLException {
        List<ProductBeanCls> products = new ArrayList<>();
        String sql = "SELECT p.*, c.category AS category_name FROM products p JOIN category c ON p.category_id = c.id WHERE p.user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProductBeanCls product = new ProductBeanCls();
                    product.setId(resultSet.getInt("id"));
                    product.setUser_id(resultSet.getInt("user_id"));
                    product.setCategory_id(resultSet.getInt("category_id"));
                    product.setPname(resultSet.getString("pname"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setDescription(resultSet.getString("description"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setPimages(resultSet.getString("pimages"));
                    product.setCategoryName(resultSet.getString("category_name"));
                    products.add(product);
                }
            }
        }
        return products;
    }
}
