package org.cinema;

import java.sql.Date;
import java.sql.Time;

class Ticket {
    private String cinemaName;
    private String filmName;
    private Date datetime;
    private Time clock;
    private int numberTickets;
    private int  price;

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
