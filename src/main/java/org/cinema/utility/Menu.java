
package org.cinema.utility;

import java.util.*;

public class Menu {
    Manager manager = new Manager();
    int command;
    String input1,input2;
    Scanner input = new Scanner(System.in);

    public Menu() {
    }

    //:::::>
    public int publicMenu(){
        System.out.println("**********WELCOME**********");
        System.out.println("1-Sign in(Enter).");
        System.out.println("2-Sign up(Register).");
        System.out.println("3-Exit.");
        System.out.print("Please select a number:");
        command = input.nextInt();
        input.nextLine();
        switch(command)
        {
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            default:
                return 0;
        }
    }

    //:::::>
    public void RegisterMenu() {
        System.out.println("Who are you?");
        System.out.println("1-Admin.");
        System.out.println("2-Cinema.");
        System.out.println("3-User.");
        System.out.print("Please select a number:");
        command = input.nextInt();
        input.nextLine();
        switch(command)
        {

            case 1:
                manager.registerAdmin();
                break;

            case 2:
                manager.registerCinema();
                break;

            case 3:
                manager.registerUser();
                break;
        }
    }

    //:::::>
    public void enterMenu() {
        System.out.print("Please enter your user name:");
        input1 = input.nextLine();
        if (manager.findInArray(input1) == -1) {
            System.out.println("this user name is not found!,please sign up and try again!");
            return;
        }
        System.out.print("Please enter your password:");
        input2 = input.nextLine();
        switch (manager.selectMenu(input1, input2))
        {
            case 0:
                System.out.println("it's very strange!");
                break;

            case 1:
                boolean isFalse = true;
                while(isFalse)
                {

                    System.out.println("**********WELCOME Admin**********");
                    System.out.println("1-Confirm Cinema Manager Account.");
                    System.out.println("2-Exit.");
                    System.out.print("Please select a number:");
                    command = input.nextInt();
                    input.nextLine();
                    switch(command)
                    {
                        case 1:
                            manager.confirmCinema();
                            break;

                        case 2:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default :
                            System.out.println("You enter a wrong number!");

                    }
                }
                break;

            case 2:
                if(manager.isConfirm(input1,input2) == 0 ) {
                    System.out.println("Unfortunately Admin not confirm you,please call to Admin!");
                    break;
                }

                isFalse = true;
                while(isFalse) {
                    System.out.println("**********WELCOME Cinema Manager**********");
                    System.out.println("1-add Ticket.");
                    System.out.println("2-cancel Ticket.");
                    System.out.println("3-*Add OFF code*");
                    System.out.println("4-*view high purchase film*");
                    System.out.println("5-Exit.");
                    System.out.print("Please select a number:");
                    command = input.nextInt();
                    input.nextLine();
                    switch (command) {
                        case 1:
                            manager.addTicket(input1,input2);
                            break;

                        case 2:
                            manager.delTicket(input1,input2);
                            break;

                        case 3:
                            manager.addOffCode();
                            break;

                        case 4:
                            manager.listHighPurchase(input1,input2);
                            break;

                        case 5:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default:
                            System.out.println("You enter a wrong number!");
                    }
                }
                break;

            case 3:
                isFalse = true;
                while(isFalse)
                {
                    System.out.println("**********WELCOME User**********");
                    System.out.println("1-Reserve Ticket.");
                    System.out.println("2-View all Tickets");
                    System.out.println("3-Search with name and date.");
                    System.out.println("4-*view my Basket*");
                    System.out.println("5-Exit.");
                    System.out.print("Please select a number:");
                    command = input.nextInt();
                    input.nextLine();
                    switch(command)
                    {
                        case 1:
                            manager.reserveTicket(input1);
                            break;

                        case 2:
                            manager.showAllTicket();
                            break;

                        case 3:
                            manager.searchFilm();
                            break;

                        case 4:
                            manager.viewMyBasket(input1);
                            break;

                        case 5:
                            System.out.println("Good luck!");
                            isFalse = false;
                            break;

                        default :
                            System.out.println("You enter a wrong number!");
                    }
                }
                break;

            default:
                System.out.println("You enter a wrong number");
        }
    }
}
