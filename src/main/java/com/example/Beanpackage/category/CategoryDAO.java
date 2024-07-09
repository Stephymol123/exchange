package com.example.Beanpackage.category;

import com.example.Beanpackage.category.CategoryBeanCls;
import com.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (category) VALUES (?)";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT id, category FROM category WHERE id = ?";
    private static final String SELECT_ALL_CATEGORIES = "SELECT id, category FROM category";
    private static final String DELETE_CATEGORY_SQL = "DELETE FROM category WHERE id = ?";
    private static final String UPDATE_CATEGORY_SQL = "UPDATE category SET category = ? WHERE id = ?";

    public void insertCategory(CategoryBeanCls category) throws SQLException {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getCategory());
            preparedStatement.executeUpdate();
        }
    }

    public CategoryBeanCls selectCategory(int id) {
        CategoryBeanCls category = null;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("category");
                category = new CategoryBeanCls(id, categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public List<CategoryBeanCls> selectAllCategories() {
        List<CategoryBeanCls> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String categoryName = rs.getString("category");
                CategoryBeanCls category = new CategoryBeanCls(id, categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public boolean deleteCategory(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCategory(CategoryBeanCls category) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {
            statement.setString(1, category.getCategory());
            statement.setInt(2, category.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
