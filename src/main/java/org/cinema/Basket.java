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
     String username;
     Integer idTicket;
     String filmName;
     Integer number;
     Integer priceAll;
}
