package com.twu.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by droman on 6/17/16.
 */
public class AppExecutor {

    private Application application;

    public AppExecutor(Application application){
        this.application = application;
    }

    public static HashMap<String, Option> setUpOptions(){
        HashMap<String, Option> options = new HashMap<String, Option>();
        ListBooksOption optionListBooks = new ListBooksOption();
        ListMoviesOption optionListMovies = new ListMoviesOption();
        ListCheckoutsOption optionListCheckouts =  new ListCheckoutsOption();
        UserDetailsOption optionUserDetails =  new UserDetailsOption();

        options.put("List Books", optionListBooks);
        options.put("List Movies", optionListMovies);
        options.put("List Checkouts", optionListCheckouts);
        options.put("User Details", optionUserDetails);

        return options;
    }


    public static LinkedHashSet<Item> setUpExampleItems(){
        LinkedHashSet<Item> items = new LinkedHashSet<Item>();

        items.add(new Book("Hobbit", "JRR Tolkien", 1937));
        items.add(new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970));
        items.add(new Book("Matilda", "Roal Dahl", 1988));

        items.add(new Movie("School of Rock", 2003, "Richard Linklater", 0));
        items.add(new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 10));
        items.add(new Movie("Gladiator", 2000, "Ridley Scott", 8));

        return items;
    }


    public static Library setUpLibrary(LinkedHashSet<Item> listItems){

        return new Library(listItems);
    }

    public static HashMap<String, User> setUpUsers(){

        HashMap<String, User> users = new HashMap<String, User>();

        PersonInfo davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");
        User david = new User(davidInfo, "111-1111", "biblioteca1");

        PersonInfo larryInfo = new PersonInfo("Larry Roman", "lroman@gmail.com", "0999353546");
        User larry = new User(larryInfo, "222-2222", "biblioteca2");

        users.put(david.getUserID(), david);
        users.put(larry.getUserID(), larry);

        return users;
    }


    public void renderLogin(Scanner scan){
        System.out.println("\nLog In with an Account\n");
        System.out.println("For the demo we have the following Accounts:");
        System.out.println("User ID: 111-1111, Password: biblioteca1");
        System.out.println("User ID: 222-2222, Password: biblioteca2\n");

        boolean loginSucessful = false;

        while(!(loginSucessful)) {
            System.out.println("Type User ID: ");
            String userID = scan.nextLine();
            System.out.println("Tyoe Password: ");
            String password = scan.nextLine();
            loginSucessful = application.login(userID, password);
            if(loginSucessful == false){
                System.out.println("\nUser ID or Password are INCORRECT\n");
            }
        }
    }

    public void renderMenuPresentation(){
        System.out.println("\nMenu of Options:");
        System.out.println(application.viewMenu());
        System.out.println("Type the Option you want to access or type 'logout' to exit the app");
    }

    public String getMenuOptionFromUser(Scanner scan){
        String option = scan.nextLine();
        return option;
    }


    public void renderOptionFromMenu(Scanner scan, String option){

        while(!application.validOption(option)){
            System.out.println("\n"+application.pickOption(option));
            System.out.println("\nType Option again:");
            option = scan.nextLine();
        }
        System.out.println("\n"+application.pickOption(option));
    }

    public Item createBookFromUser(Scanner scan){
        System.out.print("Type Title:");
        String title = scan.nextLine();
        System.out.print("Type Author:");
        String author = scan.nextLine();
        System.out.print("Type Year Published:");
        int yearPublished = Integer.parseInt(scan.nextLine());

        return new Book(title, author, yearPublished);
    }

    public Item createMovieFromUser(Scanner scan){
        System.out.print("Type Name:");
        String name = scan.nextLine();
        System.out.print("Type Year:");
        int year = Integer.parseInt(scan.nextLine());
        System.out.print("Type Director:");
        String director = scan.nextLine();
        System.out.print("Rating:");
        int rating = Integer.parseInt(scan.nextLine());

        return new Movie(name, year, director, rating);
    }

    public Item createItemFromUser(Scanner scan, String typeOfItem){
        if(typeOfItem.equals("book")){
            return createBookFromUser(scan);
        }

        return createMovieFromUser(scan);
    }


/*
    public void checkoutOrReturnFromUser(String option, Item targetItem){
        if (option.equals("checkout")) {
            System.out.println("\n"+library.checkOut(targetItem, application.getCurrentUser());
        } else if (option.equals("return")) {
            System.out.println("\n"+library.returnItem(targetItem));
        }
    }
*/

    public void checkoutOrReturnFromUser(String option, Item targetItem){
        if (option.equals("checkout")) {
            System.out.println("\n"+application.checkoutFromUser(targetItem));
        } else if (option.equals("return")) {
            System.out.println("\n"+application.returnFromUser(targetItem));
        }
    }


    public void executeCheckoutReturnOption(Scanner scan){

        String instructionInitialAction = "\nType 'checkout' to checkout items, " +
                "\nType 'return' to return items " +
                "\nOr Type 'menu' to go back to Main Menu:";

        String instructionPickTypeItem = "\nType 'book' to execute checkout/return  a book" +
                "\nOr Type 'movie' to checkout/return  a movie";

        System.out.println(instructionInitialAction);
        String option = scan.nextLine();

        while(!option.equals("menu")){
            System.out.println(instructionPickTypeItem);
            String typeOfItem = scan.nextLine();

            Item targetItem = createItemFromUser(scan, typeOfItem);
            checkoutOrReturnFromUser(option, targetItem);

            System.out.println(instructionInitialAction);
            option = scan.nextLine();
        }
    }

    public boolean validExitOption(String response){
        return response.equals("exit") || response.equals("login");
    }

    public boolean decisionToExitApp(Scanner scan){
        System.out.println("\nIf you want to exit the App type 'exit' or type 'login' to Log In into an account");
        String response = scan.nextLine();

        while(!validExitOption(response)){
            System.out.println("\nYou selected an invalid option. You can type 'exit' or 'login'");
            response = scan.nextLine();
        }

        if(response.equals("exit")){
            return true;
        }

        return false;
    }


    public void executeApplication(Scanner scan){

        boolean exitApp = false;

        while(!exitApp) {

            renderLogin(scan);
            renderMenuPresentation();
            String option = getMenuOptionFromUser(scan);

            while (!option.equals("logout")) {
                renderOptionFromMenu(scan, option);
                executeCheckoutReturnOption(scan);
                renderMenuPresentation();
                option = getMenuOptionFromUser(scan);
            }

            exitApp = decisionToExitApp(scan);
        }

        System.out.println("\nThank for using the Online Bangalore Public Library");

        System.exit(0);
    }


}
