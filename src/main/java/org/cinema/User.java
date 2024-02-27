package org.cinema;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User extends Person{

    //::::>
    public User(String firstName,String lastName,String username,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

}
