package org.cinema;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
        private Connection connection;


    //::::>
    public AdminRepository(Connection connection) throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS admin\n" +
                "(\n" +
                "    id         SERIAL PRIMARY KEY,\n" +
                "    first_name VARCHAR(50),\n" +
                "    last_name VARCHAR(50),\n" +
                "    username VARCHAR(50) NOT NULL ,\n" +
                "    password VARCHAR(50)\n" +
                ")";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.executeUpdate();
    }

    //::::>
    public int importAdmin(Admin admin) throws SQLException {
        String importValue = "INSERT Admin(firstName,lastName,username,password) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,admin.firstName);
        preparedStatement.setString(2,admin.lastName);
        preparedStatement.setString(3,admin.username);
        preparedStatement.setString(4,admin.password);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public String findAdmin(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Admin WHERE username = ? AND password = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return resultSet.getString("username");
        else
            return null;
    }







}
