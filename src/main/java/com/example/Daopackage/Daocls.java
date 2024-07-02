package com.example.Daopackage;

import com.example.Beanpackage.Beancls;
import com.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Daocls {

    private static final String INSERT_USERS_SQL = "INSERT INTO users (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT id, username, email FROM users WHERE username = ? and password = ?";

    private Connection getConnection() throws SQLException {
        // Implement your database connection logic here
        // Make sure you have the JDBC driver loaded and configured properly
        Connection connection = null;
        try {
            // Example for SQLite (make sure to adjust this according to your database setup)
            connection = DatabaseConnection.connect();
        } catch (SQLException e) {
            throw new SQLException("Error connecting to database", e);
        }
        return connection;
    }

    public void registerUser(Beancls beancls) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, beancls.getFirstname());
            preparedStatement.setString(2, beancls.getLastname());
            preparedStatement.setString(3, beancls.getUsername());
            preparedStatement.setString(4, beancls.getPassword());
            preparedStatement.setString(5, beancls.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error registering user", e);
        }
    }

    public Beancls validateUser(String username, String password) throws SQLException {
        Beancls beancls = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                beancls = new Beancls();
                beancls.setId(rs.getInt("id"));
                beancls.setUsername(rs.getString("username"));
                beancls.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error validating user", e);
        }
        return beancls;
    }
}
