package com.twu.biblioteca;

import java.util.*;


public class BibliotecaApp {

    private String welcomeMessage = "Welcome to the Bangalore Public Library!";
    private LinkedHashMap<Book, Availability> books;
    private HashMap<String, Option> options;


    public BibliotecaApp(LinkedHashMap<Book, Availability> books, HashMap<String, Option> options){
        this.books = books;
        this.options = options;
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

    public LinkedHashMap<Book, Availability> getBooks(){
        return books;
    }

    public Boolean isBookInLibrary(Book book){
        return books.containsKey(book);
    }

    public Availability getAvailability(Book book){
        //Check if the book is in the library
        if(isBookInLibrary(book)){
            //Return the availability status of the book
            return books.get(book);
        }
        //If the book is not in the library return that is unavailable
        return Availability.UNAVAILABLE;
    }


    //This method changes the status of availability (AVAILABLE or UNAVAILABLE)
    public void changeStatus(Book book, Availability newStatus){
        if(isBookInLibrary(book)){
            books.put(book, newStatus);
        }
    }



    public Boolean isBookAvailable(Book book){

        if(isBookInLibrary(book)) {
            Availability status = getAvailability(book);
            if (status == Availability.AVAILABLE) {
                return true;
            }
        }

        return false;
    }

    public Book findBook(Book bookToFind){

        if(isBookInLibrary(bookToFind)){
            for(Book book : books.keySet()){
                if(book.equals(bookToFind)){
                    //Return the reference of the book in the list
                    return book;
                }
            }
        }
        return null;
    }

    public String checkOut(Book bookToCheckout){

        //Find the book. Gives me the reference of the book in the HashMap that is equal to the
        //book passed
        Book bookFound = findBook(bookToCheckout);

        if(isBookAvailable(bookFound)){
            changeStatus(bookToCheckout, Availability.UNAVAILABLE);
            return "Thank you! Enjoy the book!";
        }
        return "That book is not available";
    }

    public String returnBook(Book book){
        //Check that book is unavailable
        if(!isBookAvailable(book) && isBookInLibrary(book)){
            changeStatus(book, Availability.AVAILABLE);
            return "Thank you for returning the book";
        }

        return "That is not a valid book to return";
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

}
