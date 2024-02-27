package org.cinema.utility;
import org.cinema.model.*;
import org.cinema.repository.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Manager {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres" , "postgres" , "shlgham");
    UserRepository userRepository = new UserRepository(connection);
    AdminRepository adminRepository = new AdminRepository(connection);
    CinemaRepository cinemaRepository = new CinemaRepository(connection);
    TicketRepository ticketRepository = new TicketRepository(connection);
    BasketRepository basketRepository = new BasketRepository(connection);
    Scanner input = new Scanner(System.in);
    String input1,firstName,lastName,username,password,cinemaName,cinemaNumber,filmName,timeDate,clock;
    int numberTickets,price,priceAll;
    String[] offCode = new String[1000];
    static Integer emptyHomeIndexOffCode = 0;
    String[] usernameArray = new String[1000];
    static Integer emptyHomeIndex = 0;

    //::::>
    public Manager() throws SQLException  {
    }

    //::::>
    public void registerAdmin() throws SQLException {
        System.out.print("Please enter your first name:");
        firstName = input.nextLine();
        System.out.print("Enter your last name:");
        lastName = input.nextLine();
        while(true){
            System.out.print("Enter your user name:");
            username = input.nextLine();
            int result = findInArray(username);
            if(result != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex++;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        Admin admin = new Admin(firstName,lastName,username,password);
        int result =  adminRepository.importAdmin(admin);
        if(result != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public void registerCinema() throws SQLException {
        while(true)
        {
            System.out.print("Please enter your Cinema name:");
            cinemaName = input.nextLine();
            if(cinemaRepository.hasCinema(cinemaName) == 1)
                System.out.println("this cinema name is defined before,select another Name");
            else
                break;
        }
        System.out.print("Enter your Cinema number:");
        cinemaNumber = input.nextLine();
        while(true) {
            System.out.print("Enter your user name:");
            username = input.nextLine();
            if( findInArray(username) != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex--;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        Cinema cinema = new Cinema(cinemaName,cinemaNumber,username,password);
        if(cinemaRepository.importCinema(cinema) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public void registerUser() throws SQLException {
        System.out.print("Please enter your first name:");
        firstName = input.nextLine();
        System.out.print("Enter your last name:");
        lastName = input.nextLine();
        while(true){
            System.out.print("Enter your user name:");
            username = input.nextLine();
            if(findInArray(username) != -1 )
                System.out.println("this user name is defined before,select another username");
            else{
                usernameArray[emptyHomeIndex] = username;
                emptyHomeIndex++;
                break;
            }
        }
        System.out.print("Enter your password:");
        password = input.nextLine();
        User user = new User(firstName,lastName,username,password);
        if(userRepository.importUser(user) != 0 )
            System.out.println("Sign up is successfully and now you can Sign In!");
        else
            System.out.println("Sing up is ERROR!,please try again");
    }

    //::::>
    public int findInArray(String username){
        for(int i=0;i<emptyHomeIndex;i++)
            if(usernameArray[i].equals(username))
                return i;
            return -2;
    }

    //::::>
    public int selectMenu(String username,String password) throws SQLException {
        if(adminRepository.findAdmin(username,password) != null )
            return 1;
        else if(cinemaRepository.findCinema(username,password) != null)
            return 2;
        else if(userRepository.findUser(username,password) != null)
            return 3;
        else
            return 0;
    }

    //::::>
    public void confirmCinema() throws SQLException {
        System.out.println("list of unConfirm cinema are:");
        cinemaRepository.showUnconfirmCinema();
        System.out.println("That's All!");
        System.out.print("Please enter name of cinema you want to confirm:");
        input1 = input.nextLine();
        if(cinemaRepository.confirmCinema(input1) == 0 )
            System.out.println("Sorry,something is wrong and your Cinema enter is not confirmed");
        else
            System.out.println(input1 + " is successful is confirmed!");

    }

    //::::>
    public void addTicket(String username,String password) throws SQLException {
            cinemaName = cinemaRepository.findCinema(username,password);
        System.out.println("please enter Information of Ticket for add!");
        System.out.print("Enter film name: ");
        filmName = input.nextLine();
        System.out.print("Enter date(yyyy-dd-mm):");
        timeDate = input.nextLine();
        Date timeD = Date.valueOf(timeDate);
        System.out.print("Enter time(HH:MM:SS):");
        clock = input.nextLine();
        Time timeC = Time.valueOf(clock);
        System.out.print("how many Ticket you has(number): ");
        numberTickets = input.nextInt();
        System.out.print("Enter price for " + filmName + " :");
        price = input.nextInt();
        input.nextLine();
        Ticket ticket = new Ticket(cinemaName,filmName,timeD,timeC,numberTickets,price);
        if(ticketRepository.importTicket(ticket) != 0 )
            System.out.println("Enter Ticket to Table successful!");
        else
            System.out.println("Unfortunately something is wrong,Please try again!");
    }

    //::::>
    public void delTicket(String username,String password) throws SQLException {
            cinemaName = cinemaRepository.findCinema(username,password);
        Date nowDate = Date.valueOf(java.time.LocalDate.now().plusDays(1000L));
        Time nowTime = Time.valueOf(java.time.LocalTime.now());
        System.out.println("**Now Date is: " + nowDate + " and Now Time is: " + nowTime + " **");
        ticketRepository.showCinemaTickets(cinemaName);
        System.out.print("Enter Id Ticket for cancel:");
        Integer idDel = input.nextInt();
        input.nextLine();
        Date dateTicket = (Date) ticketRepository.returnDateTime(idDel);
        if(dateTicket == null){
            System.out.println("You Enter a wrong id!");
            return;
        }
        if(dateTicket.after(nowDate)){
            basketRepository.cancelTicket(idDel);
            ticketRepository.delTicket(idDel);
            System.out.println("Ticket you enter successful cancel!");
        }
        else if(dateTicket.equals(nowDate)){
            Time timeTicket = ticketRepository.returnClock(idDel);
            if(timeTicket == null){
                System.out.println("Something wrong!");
                return;
            }
            if(timeTicket.after(nowTime)){
                basketRepository.cancelTicket(idDel);
                ticketRepository.delTicket(idDel);
                System.out.println("Ticket you enter successful cancel!");
            }
            else
                System.out.println("this time ticket you enter is Expired!");
        }
        else
            System.out.println("this time ticket you enter is Expired!");

    }

    //::::>
    public void showAllTicket() throws SQLException {
        ticketRepository.showAllTicket();
    }

    //::::>
    public void searchFilm() throws SQLException {
        System.out.print("Please enter film name:");
        input1 = input.nextLine();
        System.out.print("Please enter film date(yyyy-dd-mm):");
        timeDate = input.nextLine();
        Date timeD = Date.valueOf(timeDate);
        ticketRepository.searchWithName(input1,timeD);
    }

    //::::>
    public void reserveTicket(String username) throws SQLException {
        showAllTicket();
        System.out.println("Please enter Information of ticket you want to reserve");
        System.out.println("Enter idTicket:");
        int id = input.nextInt();
        String[] ticketInformation = ticketRepository.getTicketInformation(id);
        if (ticketInformation == null){
            System.out.println("You enter a wrong id Ticket!");
            return;
        }
        filmName = ticketInformation[0];
        System.out.print("How many ticket you want to buy?:");
        numberTickets = input.nextInt();
        if(numberTickets > Integer.parseInt(ticketInformation[1])){
            System.out.println("you enter a number bigger than numberOfTicket we have");
            return;
        }
        if(ticketRepository.updateNumberOfTicket(id,Integer.parseInt(ticketInformation[1]) - numberTickets) == 0 ){
            System.out.println("Something Wrong");
            return;
        }
        priceAll = calcPriceAll(numberTickets,Integer.parseInt(ticketInformation[2]));
        int allBuy = ticketRepository.allBuyTicket(id) + numberTickets;
        ticketRepository.updateNumberTicketBuy(id,allBuy);
        Basket basket = new Basket(username,id,filmName,numberTickets,priceAll);
        if(basketRepository.importTicket(basket) != 0)
            System.out.println("Ticket you Enter successful add to you Basket!");
        else
            System.out.println("Something Wrong!");
    }

    //::::>
    public void viewMyBasket(String username) throws SQLException {
        basketRepository.viewMyBasket(username);
    }

    //::::>
    public void addOffCode(){
        System.out.print("Please enter name of OFF Code(name-percentage):");
        offCode[emptyHomeIndexOffCode] = input.nextLine();
        emptyHomeIndexOffCode++;
        System.out.println("this off code successful add!");
    }

    public int searchOffCode(String off){
        for(int i=0;i<emptyHomeIndexOffCode;i++)
            if(offCode[i].equals(off))
                return 1;
        return 0;
    }

    //::::>
    public int calcPriceAll(int number,int price){
        input.nextLine();
        System.out.print("if you have a OFF code,Please enter it:");
        String off = input.nextLine();
        if(searchOffCode(off) == 0 ){
            System.out.println("You dont have a OFF code,and you ticket calc with normal price!");
            return number*price;
        }
        else{
            String[] array =off.split("-");
            int mountPercentage = Integer.parseInt(array[1]);
            double finalPercentage = (100.0 - mountPercentage ) / 100;
            return (int) ((number*price) * finalPercentage);
        }

    }

    //::::>
    public void listHighPurchase(String username,String password) throws SQLException {
        cinemaName = cinemaRepository.findCinema(username,password);
        ticketRepository.showListHighPurchase(cinemaName);
    }

    //::::>
    public int isConfirm(String username,String password) throws SQLException {
        cinemaName = cinemaRepository.findCinema(username,password);
        if(cinemaRepository.isConfirm(cinemaName) == 0 )
            return 0;

        return 1;
    }



}
