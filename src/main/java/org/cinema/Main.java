//In the name of God!

package org.cinema;


import org.cinema.utility.Menu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.cinema.Main");
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
