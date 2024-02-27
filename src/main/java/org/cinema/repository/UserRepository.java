package org.cinema.repository;

import org.cinema.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final Connection connection;


    //::::>
   public UserRepository(Connection connection) throws SQLException {
       this.connection = connection;
       String create = "CREATE TABLE IF NOT EXISTS usertable(first_name varchar(50),last_name varchar(50),username varchar(50)PRIMARY KEY, password varchar(50) ) ";
       PreparedStatement preparedStatement = connection.prepareStatement(create);
       preparedStatement.execute();
   }

    //::::>
    public int importUser(User user) throws SQLException {
        String importValue = "INSERT INTO usertable (first_name, last_name ,username,password) VALUES (? ,? ,? ,? )";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,user.getFirstName());
        preparedStatement.setString(2,user.getLastName());
        preparedStatement.setString(3,user.getUsername());
        preparedStatement.setString(4,user.getPassword());
        return preparedStatement.executeUpdate();
    }

    //::::>
    public String findUser(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Usertable WHERE username = ? AND password = ? ";
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
