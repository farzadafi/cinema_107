package org.cinema.repository;

import org.cinema.model.Basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private final Connection connection;

    public BasketRepository(Connection connection) {
        this.connection = connection;
        String createTable = " CREATE TABLE IF NOT EXISTS basket(id SERIAL PRIMARY KEY ,username varchar(50) REFERENCES UserTable(username)," +
                "ticket_id INTEGER REFERENCES Ticket(id),film_name varchar(50),ticket_number Integer,price_all Integer) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //::::>
    public int importTicket(Basket basket) {
        String importBasket = "INSERT INTO basket(username,ticket_id,film_name,ticket_number,price_all) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importBasket);
            preparedStatement.setString(1, basket.getUsername());
            preparedStatement.setInt(2, basket.getTicketId());
            preparedStatement.setString(3, basket.getFilmName());
            preparedStatement.setInt(4, basket.getNumber());
            preparedStatement.setInt(5, basket.getPriceAll());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public void cancelTicket(Integer id) {
        String cancel = "DELETE FROM Basket WHERE id = ?; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(cancel);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public void viewMyBasket(String username) {
        String findUser = " SELECT * FROM Basket WHERE username = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findUser);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id=" + resultSet.getInt("id") +
                        "  |film name=" + resultSet.getString("film_name") +
                        "   |Ticket number=" + resultSet.getInt("ticket_number") +
                        "   |Price all=" + resultSet.getInt("price_all"));
            }
            System.out.println("That's all");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}
