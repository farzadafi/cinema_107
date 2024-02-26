//In the name of God!

package org.cinema;

import org.cinema.utility.Menu;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.cinema.Main");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
