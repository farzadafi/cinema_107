package org.cinema;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Basket {
     String basketId;
     Integer ticketId;
     String filmName;
     Integer numberOfTickets;
     Integer priceAll;
}
