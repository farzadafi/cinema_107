package org.cinema;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
        private Connection connection;


    //::::>
    public AdminRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = "CREATE TABL NOT EXISTS Admin(firstName varcha(5),lastName varchar(50),username varcar(50)not null, password varchar(50) )";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importAdmin(Admin admin) throws SQLException {
        String importValue = "INSERT Admin(firstName,lastName,username) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,admin.firstName);
        preparedStatement.setString(2,admin.lastName);
        preparedStatement.setString(3,admin.username);
        preparedStatement.setString(5,admin.password);
        return preparedStatement.execute();
    }

    //::::>
    public String findAdmin(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Admin WHERE uername = ? AND pass = ? ";
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
