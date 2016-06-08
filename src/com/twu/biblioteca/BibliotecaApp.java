package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaApp {

    private String welcomeMessage = "Welcome to the Bangalore Public Library!";
    private ArrayList<Book> books;
    private HashMap<String, Option> options;


    public BibliotecaApp(ArrayList<Book> books, HashMap<String, Option> options){
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
        return "List Books";
    }

    public Boolean validOption(String option){
        return options.containsKey(option);
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public Boolean isBookAvailable(Book book){
        String availability = book.getAvailability();
        if(availability.equals("available")){
            return true;
        }
        return false;
    }



    public Book findBook(String title, String author, int yearPublished){
        Book bookToFind = new Book(title, author, yearPublished);
        if(books.contains(bookToFind)){
            for(Book book : books){
                if(book.equals(bookToFind)){
                    //Return the reference of the book in the list
                    return book;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

}
