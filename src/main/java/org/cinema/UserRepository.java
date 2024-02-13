package org.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;


    //::::>
   public UserRepository(Connection connectionn) throws SQLException {
       connection = connectionn;
       String create = "CREATE TABLE IF NOT EXISTS UserTable (firstName varchar(50),lastName varchar(50),username varchar(50)PRIMARY KEY,password varchar(50) ) ";
       PreparedStatement far = connection.prepareStatement(create);
       far.execute();
   }

    //::::>
    public int importUser(User user) throws SQLException {
        String importValue = "INSERT INTO usertable (username,password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importValue);
        preparedStatement.setString(1,user.firstName);
        preparedStatement.setString(2,user.lastName);
        preparedStatement.setString(3,user.username);
        preparedStatement.setString(4,user.password);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public String findUser(String username,String password) throws SQLException {
        String findQuery = "SELECT * FROM Usertable WHER username = ? AND password = ? ";
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
