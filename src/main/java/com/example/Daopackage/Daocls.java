package com.example.Daopackage;

import com.example.Beanpackage.Beancls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Daocls {

    private String jdbcURL = "jdbc:mysql://localhost:3306/exchangedb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (firstname, lastname, phnno, username, password, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT id, username, email FROM users WHERE username = ? and password = ?";

    public Daocls() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void registerUser(Beancls beancls) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, beancls.getFirstname());
            preparedStatement.setString(2, beancls.getLastname());
            preparedStatement.setInt(3, beancls.getPhnno());
            preparedStatement.setString(1, beancls.getUsername());
            preparedStatement.setString(2, beancls.getPassword());
            preparedStatement.setString(3, beancls.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Beancls validateUser(String username, String password) {
        Beancls beancls= null;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beancls;
    }
}
