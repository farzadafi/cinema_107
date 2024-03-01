package org.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;


    //::::>
    public UserRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String create = "CREATE TABLE IF NOT EXISTS user_table\n" +
                "(\n" +
                "    first_name VARCHAR(50),\n" +
                "    last_name  VARCHAR(50),\n" +
                "    username   VARCHAR(50) PRIMARY KEY,\n" +
                "    password   VARCHAR(50)\n" +
                ")";
        PreparedStatement ps = connection.prepareStatement(create);
        ps.executeUpdate();
    }

    //::::>
    public int importUser(User user) throws SQLException {
        String importValue = "INSERT INTO usertable (firstName,lastName,username,password) VALUES (? ,? ,?, ?)";
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
