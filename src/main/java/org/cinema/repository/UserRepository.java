package org.cinema.repository;

import org.cinema.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final Connection connection;


    //::::>
   public UserRepository(Connection connection) {
       this.connection = connection;
       String create = "CREATE TABLE IF NOT EXISTS UserTable (firstName varchar(50)," +
               "lastName varchar(50),username varchar(50)PRIMARY KEY,password varchar(50) ); ";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(create);
           preparedStatement.execute();
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }

    //::::>
    public int importUser(User user) {
        String importValue = "INSERT INTO usertable (firstname, lastname, username,password) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importValue);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    //::::>
    public String findUser(String username,String password) {
        String findQuery = "SELECT * FROM Usertable WHERE username = ? AND password = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getString("username");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
