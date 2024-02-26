package org.cinema.repository;

import org.cinema.model.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRepository {
    private final Connection connection;

    //::::>
    public CinemaRepository(Connection connection){
        this.connection = connection;
        String createTable = "CREATE TABLE IF NOT EXISTS Cinema (cinema_name varchar(50)PRIMARY KEY,cinema_number varchar(50),username varchar(50),password varchar(50),confirm int )";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public int importCinema(Cinema cinema){
        String importValue = "INSERT INTO Cinema (cinema_name,cinema_number,username,password,confirm) VALUES (?, ?, ?, ? , ?) ";
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importValue);
            preparedStatement.setString(1, cinema.getCinemaName());
            preparedStatement.setString(2, cinema.getCinemaNumber());
            preparedStatement.setString(3, cinema.getUsername());
            preparedStatement.setString(4, cinema.getPassword());
            preparedStatement.setInt(5, 10);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    //::::>
    public String findCinema(String username,String password) {
        String findQuery = "SELECT * FROM Cinema WHERE username = ? AND password = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getString("cinema_name");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //::::>
    public void showUnconfirmedCinema() {
        String findQuery = "SELECT * FROM Cinema WHERE confirm = 10; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Cinema Name[" + resultSet.getString("cinema_name") + "] and Cinema Number[" + resultSet.getString("cinema_number") + "]");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public int confirmCinema(String cinemaName) {
        String confirm = "UPDATE Cinema SET confirm = ? WHERE cinema_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(confirm);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, cinemaName);
            if (preparedStatement.executeUpdate() == 0)
                return 0;
            else
                return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public int hasCinema(String cinemaName) {
        String has = "SELECT * FROM Cinema WHERE cinema_name = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(has);
            preparedStatement.setString(1, cinemaName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public int isConfirm(String CinemaName) {
        String isConfirmCinema = "SELECT confirm FROM Cinema WHERE cinema_name = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(isConfirmCinema);
            preparedStatement.setString(1, CinemaName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("confirm");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }






}
