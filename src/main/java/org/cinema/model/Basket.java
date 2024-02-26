package org.cinema.model;

public class Basket {
    private final String username;
    private final Integer ticketId;
    private final String filmName;
    private final Integer number;
    private final Integer priceAll;

    public Basket(String Username,Integer ticketId,String filmName,Integer number,Integer priceAll){
        this.username = Username;
        this.ticketId = ticketId;
        this.filmName = filmName;
        this.number = number;
        this.priceAll = priceAll;
    }

    public String getUsername() {
        return username;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public String getFilmName() {
        return filmName;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPriceAll(){
        return priceAll;
    }
}
