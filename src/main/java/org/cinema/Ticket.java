package org.cinema;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString

class Ticket {
    String cinemaName;
    String filmName;
    Date datetime;
    Time clock;
    int numberTickets;
    int price;
}
