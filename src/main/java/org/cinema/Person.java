package org.cinema;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@ToString

public class Person {
     String firstName;
     String lastName;
     String username;
     String password;

}
