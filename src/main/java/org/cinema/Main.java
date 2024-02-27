//In the name of God!

package or.cinema;

import java.sql.*;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.ostgresql.river");
        Menu menu = new Menu();

        while(true)
        {
            switch(menu.publicMenu())
            {
                case 1:
                    menu.enterMenu();
                    break;

                case 2:
                    menu.RegisterMenu();
                    break;

                case 30:
                    System.out.println("Have a nice day!");
                    System.exit(0);

                case 0 :
                    System.out.println("You enter a wrong number!");

            }
        }
    }
}
