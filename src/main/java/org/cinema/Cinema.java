package org.cinema;

public class Cinema {
    private String cinemaName;
    private String cinemaNumber;
    private String username;
    private String password;
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
