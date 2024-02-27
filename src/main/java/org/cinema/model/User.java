package org.cinema.model;

public class User extends Person {

    //::::>
    public User(String firstName,String lastName,String username,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
