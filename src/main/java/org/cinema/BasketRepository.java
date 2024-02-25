package org.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRepository {
    private Connection connection;

    public BasketRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = " CREATE TABLE IF NOT Basket(id serial,username varchar(50) REFERENCS UserTable(username)," +
                       "idTicket Integer REFERENCES TicketTable(id),filmName varcar(50),numberTicket Inteer,priceAll Integer) ";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importTicket(Basket basket) throws SQLException {
        String importBasket = "INSERT INTO basket(usernme,idTicket,filmName,numberTicet,priceAll) VALUES (?, ?, ?, ?,?)";
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
        String cancel = "DELETE FROM basket";
        PreparedStatement preparedStatement = connection.prepareStatement(cancel);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        String findUser = " SELECT * FROM basket WHERE userame = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(findUser);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("id=" + resultSet.getInt("id") + "  |filmName=" + resultSet.getString("filmName") +
                                   "   |numberTicket=" + resultSet.getInt("numberTicket") + "   |priceAll=" + resultSet.getInt("priceAll"));

            }
        System.out.println("That's all");
    }







}
