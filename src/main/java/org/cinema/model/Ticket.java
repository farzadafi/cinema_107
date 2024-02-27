package org.cinema.model;

import java.sql.Date;
import java.sql.Time;

public class Ticket {
    private final String cinemaName;
    private final String filmName;
    private final Date datetime;
    private final Time clock;
    private final int numberTickets;
    private final int  price;

    public Ticket(String cinemaName,String filmName,Date datetime,Time clock,int numberTickets,int price){
        this.cinemaName = cinemaName;
        this.filmName = filmName;
        this.datetime = datetime;
        this.clock = clock;
        this.numberTickets = numberTickets;
        this.price = price;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getFilmName() {
        return filmName;
    }

    public Date getDatetime(){
        return datetime;
    }

    public Time getClock() {
        return clock;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

    public int getPrice() {
        return price;
    }
}
