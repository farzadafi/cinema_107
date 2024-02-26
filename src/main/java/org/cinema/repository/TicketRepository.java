package org.cinema.repository;

import org.cinema.model.Ticket;

import java.sql.*;
import java.util.Date;

public class TicketRepository {
    private final Connection connection;

    //::::>
    public TicketRepository(Connection connection) {
        this.connection = connection;
        String createTable = "CREATE TABLE IF NOT EXISTS ticket (id SERIAL PRIMARY KEY," +
                "cinema_name varchar(50) REFERENCES cinema(cinema_name),film_name varchar(50)," +
                "date_time date,clock time,ticket_number int,price int,number_buy int );";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //::::>
    public int importTicket(Ticket ticket) {
        String importTic = "INSERT INTO ticket(cinema_name,film_name,date_time,clock,ticket_number,price,number_buy) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importTic);
            preparedStatement.setString(1, ticket.getCinemaName());
            preparedStatement.setString(2, ticket.getFilmName());
            preparedStatement.setDate(3, ticket.getDatetime());
            preparedStatement.setTime(4, ticket.getClock());
            preparedStatement.setInt(5, ticket.getNumberTickets());
            preparedStatement.setInt(6, ticket.getPrice());
            preparedStatement.setInt(7, 0);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public void showCinemaTickets(String cinemaName) {
        String showCinema = "SELECT * FROM ticket WHERE cinema_name = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(showCinema);
            preparedStatement.setString(1, cinemaName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println("id=" + resultSet.getString("id") +
                        "    |cinema name=" + resultSet.getString("cinema_name") +
                        "    |film name =" + resultSet.getString("film_name") +
                        "    |date= " + resultSet.getString("date_time") +
                        "    |clock =" + resultSet.getString("clock"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //::::>
    public void delTicket(Integer id) {
        String del = "DELETE FROM ticket WHERE id = ?; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(del);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public Date returnDateTime(Integer id) {
        String dateTime = "SELECT date_time FROM Ticket WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(dateTime);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getDate("date_time");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Time returnClock(Integer id) {
        String clock = "SELECT clock FROM Ticket WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(clock);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getTime("clock");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //::::>
    public void showAllTicket() {
        String show = "SELECT * FROM Ticket ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(show);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println("id=" + resultSet.getString("id") +
                        "    |cinema name=" + resultSet.getString("cinema_name") +
                        "    |film name =" + resultSet.getString("film_name") +
                        "    |Date= " + resultSet.getString("date_time") +
                        "    |clock=" + resultSet.getString("clock") +
                        "    |Ticket number= " + resultSet.getInt("ticket_number") +
                        "    |price= " + resultSet.getInt("price") +
                        "    |Number of buy= " + resultSet.getInt("number_buy"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public void searchWithName(String filmName,Date timeDate) {
        String searchName = " SELECT * FROM Ticket WHERE film_name = ? AND date_time = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchName);
            preparedStatement.setString(1, filmName);
            preparedStatement.setDate(2, (java.sql.Date) timeDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println("id=" + resultSet.getString("id") +
                        "    |cinema name=" + resultSet.getString("cinema_name") +
                        "    |filmName =" + resultSet.getString("film_name") +
                        "    |date time= " + resultSet.getString("date_time") +
                        "    |clock = " + resultSet.getString("clock") +
                        "    |ticket number= " + resultSet.getInt("ticket_number") +
                        "    |price= " + resultSet.getInt("price") +
                        "    |Number of buy= " + resultSet.getInt("number_buy"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public String[] getTicketInformation(int id) {
        String[] resultArray = new String[3];
        String information = "SELECT * FROM Ticket WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(information);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultArray[0] = resultSet.getString("film_name");
                resultArray[1] = String.valueOf(resultSet.getInt("ticket_number"));
                resultArray[2] = String.valueOf(resultSet.getInt("price"));
                return resultArray;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //::::>
    public int updateNumberOfTicket(int id,Integer ticketNumber) {
        String updateNumberTicket = "UPDATE Ticket SET ticket_number = ? WHERE id = ?; ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNumberTicket);
            preparedStatement.setInt(1, ticketNumber);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //::::>
    public int allBuyTicket(int id) {
        String number = "SELECT * FROM Ticket WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(number);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("number_buy");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    //::::>
    public void updateNumberTicketBuy(int id,int number) {
        String updateNumber = " UPDATE Ticket SET number_buy = ? WHERE id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNumber);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //::::>
    public void showListHighPurchase(String cinemaName) {
        String show = "SELECT * FROM ticket WHERE cinema_name = ? ORDER BY number_buy DESC";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(show);
            preparedStatement.setString(1, cinemaName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                System.out.println("id=" + resultSet.getString("id") +
                        "    |cinema name=" + resultSet.getString("cinema_name") +
                        "    |film name =" + resultSet.getString("film_name") +
                        "    |Date= " + resultSet.getString("date_time") +
                        "    |clock=" + resultSet.getString("clock") +
                        "    |Ticket number= " + resultSet.getInt("ticket_number") +
                        "    |price= " + resultSet.getInt("price") +
                        "    |number of buy= " + resultSet.getInt("number_buy"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
