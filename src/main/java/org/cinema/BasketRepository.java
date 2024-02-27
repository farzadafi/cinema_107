package org.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private Connection connection;

    public BasketRepository(Connection connection) throws SQLException {
        this.connection = connection;

        String createTable = "CREATE TABLE IF NOT EXISTS basket\n" +
                "(\n" +
                "    id            SERIAL PRIMARY KEY,\n" +
                "    username      VARCHAR(50) REFERENCES user_table (username),\n" +
                "    ticket_id     int REFERENCES ticket (id),\n" +
                "    film_name     VARCHAR(50),\n" +
                "    ticket_number int,\n" +
                "    price_all     int\n" +
                ")";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.executeUpdate();
    }

    //::::>
    public int importTicket(Basket basket) throws SQLException {
        String importBasket = "INSERT INTO cinema.public.basket (ticket_id, user_id, film_name, ticket_number, price_all) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(importBasket);
        preparedStatement.setString(1,basket.getBasketId());
        preparedStatement.setInt(2,basket.getTicketId());
        preparedStatement.setString(3,basket.getFilmName());
        preparedStatement.setInt(4,basket.getNumberOfTickets());
        preparedStatement.setInt(5,basket.getPriceAll());
        return preparedStatement.executeUpdate();
    }

    //::::>
    public void cancelTicket(Integer id) throws SQLException {
        String cancel = "DELETE FROM cinema.public.basket";
        PreparedStatement preparedStatement = connection.prepareStatement(cancel);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        String finduser = " SELECT * FROM Basket WHERE userame = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(finduser);
        preparedStatement.setString(2,username);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("id=" + resultSet.getInt("id") + "  |filmName=" + resultSet.getString("filmName") +
                                   "   |numberTicket=" + resultSet.getInt("numberTicket") + "   |priceAll=" + resultSet.getInt("priceall"));

            }
        System.out.println("That's all");
    }







}
