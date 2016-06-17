package com.twu.biblioteca;

import java.util.*;

public class Application {

    private String welcomeMessage = "Welcome to the Online Bangalore Public Library!";
    private HashMap<String, Option> options;
    private Library library;
    private HashMap<String, User> users;
    private User currentUser;


    public Application(HashMap<String, Option> options, Library library, HashMap<String, User> users){
        this.options = options;
        this.library = library;
        this.users = users;
    }

    public String welcome(){
        return welcomeMessage;
    }


    public String pickOption(String optionName){
        if(validOption(optionName)) {
            Option option = options.get(optionName);
            return option.executeOption(this);
        }
        return "Select a valid option!";
    }

    public void addOption(String optionName, Option option){
        options.put(optionName, option);
    }

    public String viewMenu(){
        String menuMessage = "";

        for(String option : options.keySet()){
            menuMessage += option + "\n";
        }

        return menuMessage;
    }

    public Boolean validOption(String option){

        return options.containsKey(option);
    }


    public static LinkedHashMap<Book, Availability> setUpExampleBooks(){
        LinkedHashMap<Book, Availability> books = new LinkedHashMap<Book, Availability>();
        books.put(new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970), Availability.AVAILABLE);
        books.put(new Book("Matilda", "Roal Dahl", 1988), Availability.AVAILABLE);
        books.put(new Book("Hobbit", "JRR Tolkien",1937), Availability.AVAILABLE);
        return books;
    }

    public static HashMap<String, Option> setUpOptions(){
        HashMap<String, Option> options = new HashMap<String, Option>();
        ListBooksOption optionListBooksOption = new ListBooksOption();
        options.put("List Books", optionListBooksOption);

        return options;
    }

    public Boolean isValidUserID(String userID){
        return users.containsKey(userID);
    }


    public Boolean login(String userID, String password){
        if(isValidUserID(userID)){

            User userToBeChecked = users.get(userID);

            if(userToBeChecked.checkPassword(password)) {
                this.currentUser = userToBeChecked;
                return true;
            }
        }

        return false;
    }

    public Library getLibrary(){
        return this.library;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    /*
    public static Library setUpLibrary(LinkedHashMap<Book, Availability> listBooks){
        return new Library(listBooks);
    }

    public void renderMenuPresentation(){
        System.out.println("\nMenu of Options:");
        System.out.println(viewMenu());
        System.out.println("Type the Option you want to access or type 'quit' to exit the app");
    }

    public String getMenuOptionFromUser(Scanner scan){
        String option = scan.nextLine();
        return option;
    }


    public void renderOptionFromMenu(Scanner scan, String option){

        while(!validOption(option)){
            System.out.println("\n"+pickOption(option));
            System.out.println("\nType Option again:");
            option = scan.nextLine();
        }
        System.out.println("\n"+pickOption(option));
    }

    public Book createBookFromUser(Scanner scan){
        System.out.print("Type Title:");
        String title = scan.nextLine();
        System.out.print("Type Author:");
        String author = scan.nextLine();
        System.out.print("Type Year Published:");
        int yearPublished = Integer.parseInt(scan.nextLine());

        return new Book(title, author, yearPublished);
    }


    public void checkoutOrReturnFromUser(String option, Book targetBook){
        if (option.equals("checkout")) {
            System.out.println("\n"+library.checkOut(targetBook));
        } else if (option.equals("return")) {
            System.out.println("\n"+library.returnBook(targetBook));
        }
    }



    public void executeCheckoutReturnOption(Scanner scan){

        String instructionMessage = "\nType 'checkout' to checkout books, " +
                                    "\nType 'return' to return books " +
                                    "\nOr Type 'menu' to go back to Main Menu:";

        System.out.println(instructionMessage);
        String option = scan.nextLine();
        while(!option.equals("menu")){
            Book targetBook = createBookFromUser(scan);
            checkoutOrReturnFromUser(option, targetBook);
            System.out.println(instructionMessage);
            option = scan.nextLine();
        }
    }



    public void executeApplication(Scanner scan){

        System.out.println(welcome());
        renderMenuPresentation();
        String option = getMenuOptionFromUser(scan);

        while(!option.equals("quit")) {
            renderOptionFromMenu(scan, option);
            executeCheckoutReturnOption(scan);
            renderMenuPresentation();
            option = getMenuOptionFromUser(scan);
        }

        System.out.println("\nThank for using the Online Bangalore Public Library");

        System.exit(0);
    }

*/

    public static void main(String[] args) {

        /*
        Application application = new Application(setUpOptions(), setUpLibrary(setUpExampleBooks()));
        Scanner scan = new Scanner(System.in);
        application.executeApplication(scan);
        */
    }

}
