package org.cinema.repository;

import org.cinema.model.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private final Connection connection;

    public BasketRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = " CREATE TABLE IF NOT EXISTS Basket(id serial PRIMARY KEY ,username varchar(50) REFERENCES UserTable(username)," +
                       "id_ticket Integer REFERENCES Ticket(id),film_name varchar(50),number_Ticket Integer,price_all Integer) ";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importTicket(Basket basket) throws SQLException {
        String importBasket = "INSERT INTO basket(username,id_Ticket,film_name,number_Ticket,price_all) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importBasket);
        preparedStatement.setString(1,basket.getUsername());
        preparedStatement.setInt(2,basket.getIdTicket());
        preparedStatement.setString(3,basket.getFilmName());
        preparedStatement.setInt(4,basket.getNumber());
        preparedStatement.setInt(5,basket.getPriceAll());
        return preparedStatement.executeUpdate();
    }

    //::::>
    public void cancelTicket(Integer id) throws SQLException {
        String cancel = "DELETE FROM Basket WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(cancel);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        String findUser = " SELECT * FROM Basket WHERE username = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findUser);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("id=" + resultSet.getInt("id") + "  |filmName=" + resultSet.getString("film_name") +
                                   "   |numberTicket=" + resultSet.getInt("number_ticket") + "   |price_all=" + resultSet.getInt("price_all"));

            }
        System.out.println("That's all");
    }







}
