package org.cinema.repository;

import org.cinema.model.Ticket;

import java.sql.*;
import java.util.Date;

public class TicketRepository {
    private final Connection connection;

    //::::>
    public TicketRepository(Connection connection) throws SQLException {
        this.connection = connection;
        String createTable = "CREATE TABLE IF NOT EXISTS Ticket(id SERIAL PRIMARY KEY,film_name varchar(50),date_time date,clock time,ticket_number int,price int,number_buy int " +
         ",cinema_name varchar(50) REFERENCES Cinema(cinema_name))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importTicket(Ticket ticket) throws SQLException {
        String importTic = "INSERT INTO ticket(cinema_name,film_name,date_time,clock,ticket_number,price,number_buy) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(importTic);
        preparedStatement.setString(1,ticket.getCinemaName());
        preparedStatement.setString(2,ticket.getFilmName());
        preparedStatement.setDate(3,ticket.getDatetime());
        preparedStatement.setTime(4,ticket.getClock());
        preparedStatement.setInt(5,ticket.getNumberTickets());
        preparedStatement.setInt(6,ticket.getPrice());
        preparedStatement.setInt(7,0);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public void showCinemaTickets(String cinemaName) throws SQLException {
        String showCinema = "SELECT * FROM ticket WHERE cinema_name = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(showCinema);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+
                    "    |cinema name=" + resultSet.getString("cinema_name") +
                    "    |film name =" + resultSet.getString("film_name") +
                    "    |date= "+ resultSet.getString("date_time") +
                    "    |clock =" + resultSet.getString("clock"));
    }

    //::::>
    public void delTicket(Integer id) throws SQLException {
        String del = "DELETE FROM Ticket WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(del);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public Date returnDateTime(Integer id) throws SQLException {
        String dateTime = "SELECT datetime FROM Ticket WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(dateTime);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getDate("date_time");
        }
        else
            return null;
    }

    public Time returnClock(Integer id) throws SQLException {
        String clock = "SELECT clock FROM Ticket WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(clock);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getTime("clock");
        }
        else
            return null;
    }
    //::::>
    public void showAllTicket() throws SQLException {
        String show = "SELECT * FROM Ticket ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id") +
                    "    |cinemaName=" + resultSet.getString("cinema_name") +
                    "    |filmName =" + resultSet.getString("film_name") +
                    "    |Date= "+ resultSet.getString("date_time") +
                    "    |clock=" + resultSet.getString("clock") +
                    "    |numberTicket= " + resultSet.getInt("ticket_number") +
                    "    |price= " + resultSet.getInt("price") +
                    "    |numberOfBuy= " + resultSet.getInt("number_buy"));
    }

    //::::>
    public void searchWithName(String filmName,Date timeDate) throws SQLException {
        String searchName = " SELECT * FROM Ticket WHERE film_name = ? AND date_time = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(searchName);
        preparedStatement.setString(1,filmName);
        preparedStatement.setDate(2, (java.sql.Date) timeDate);
        ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinema_name")
                        + "    |filmName =" + resultSet.getString("film_name")+"    |datetime= "+ resultSet.getString("date_time")
                        + "    |clock = " + resultSet.getString("clock")
                        + "    |numberTicket= " + resultSet.getInt("ticket_number") + "     |price= " + resultSet.getInt("price") + "    | numberOfBuy= " + resultSet.getInt("number_buy"));

    }

    //::::>
    public String[] getTicketInformation(int id) throws SQLException {
        String[] resultArray = new String[3];
        String information = "SELECT * FROM Ticket WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(information);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            resultArray[0] = resultSet.getString("film_name");
            resultArray[1] = String.valueOf(resultSet.getInt("ticket_number"));
            resultArray[2] = String.valueOf(resultSet.getInt("price"));
            return resultArray;
        }
        else
            return null;
    }

    //::::>
    public int updateNumberOfTicket(int id,Integer number) throws SQLException {
        String updateNumberTicket = "UPDATE Ticket SET ticket_number = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumberTicket);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public int allBuyTicket(int id) throws SQLException {
        String number = " SELECT * FROM Ticket WHERE id = ? " ;
        PreparedStatement preparedStatement = connection.prepareStatement(number);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("number_buy");
    }

    //::::>
    public void updateNumberTicketBuy(int id,int number) throws SQLException {
        String updateNumber = " UPDATE Ticket SET number_buy = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumber);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void showListHighPurchase(String cinemaName) throws SQLException {
        String show = "SELECT * FROM ticket WHERE cinema_name = ? ORDER BY number_buy DESC" ;
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinema_name")
                    + "    |filmName =" + resultSet.getString("film_name")+"    |Date= "+ resultSet.getString("date_time") + "    |clock=" + resultSet.getString("clock")
                    + "    |numberTicket= " + resultSet.getInt("ticket_number") + "     |price= " + resultSet.getInt("price") + "     |numberOfBuy= " + resultSet.getInt("number_buy"));
    }






}
