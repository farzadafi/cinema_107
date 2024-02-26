package org.cinema.repository;


import org.cinema.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
        private final Connection connection;


    //::::>
    public AdminRepository(Connection connection){
        this.connection = connection;
        String createTable = "CREATE TABLE IF NOT EXISTS admin(id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR(50),last_name VARCHAR(50)," +
                "username VARCHAR(50) not null, password VARCHAR(50) );";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //::::>
    public int importAdmin(Admin admin) {
        String importValue = "INSERT INTO admin(first_name,last_name,username,password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importValue);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getPassword());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public String findAdmin(String username,String password){
        String findQuery = "SELECT * FROM Admin WHERE username = ? AND password = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getString("username");
            else
                return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
