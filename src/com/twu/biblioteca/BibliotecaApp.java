package com.twu.biblioteca;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class BibliotecaApp {

    private String welcomeMessage = "Welcome to the Online Bangalore Public Library!";
    private HashMap<String, Option> options;
    private Library library;


    public BibliotecaApp(HashMap<String, Option> options, Library library){
        this.options = options;
        this.library = library;
    }

    public String welcome(){
        return welcomeMessage;
    }


    public String pickOption(String optionName){
        if(validOption(optionName)) {
            Option option = options.get(optionName);
            return option.executeOption(library);
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
        ListBooks optionListBooks = new ListBooks();
        options.put("List Books", optionListBooks);

        return options;
    }

    public static Library setUpLibrary(LinkedHashMap<Book, Availability> listBooks){
        Library library = new Library(listBooks);
        return library;
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



    public static void main(String[] args) {

        BibliotecaApp application = new BibliotecaApp(setUpOptions(), setUpLibrary(setUpExampleBooks()));
        Scanner scan = new Scanner(System.in);
        application.executeApplication(scan);
    }

}
