package org.cinema;

import org.cinema.Person;

public class Admin extends Person {

    private Admin(String FirstName,String stName,String username,String password){
        this.firstName = FirstName;
        this.lastName = stName;
        this.username = username;
        this.password = password;
    }


    private Admin(){

    }

}
