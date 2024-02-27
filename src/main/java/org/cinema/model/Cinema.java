package org.cinema.model;

public class Cinema {
    private final String cinemaName;
    private final String cinemaNumber;
    private final String username;
    private final String password;
    private int confirm;

    public Cinema(String cinemaName,String cinemaNumber,String username,String password){
        this.cinemaName = cinemaName;
        this.cinemaNumber = cinemaNumber;
        this.username = username;
        this.password = password;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getCinemaNumber() {
        return cinemaNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getConfirm() {
        return confirm;
    }
}
