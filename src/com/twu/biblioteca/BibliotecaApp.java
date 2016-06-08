package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashSet;

public class BibliotecaApp {

    private String welcomeMessage = "Welcome to the Bangalore Public Library!";
    private ArrayList<Book> books;
    private HashSet<String> options;


    public BibliotecaApp(ArrayList<Book> books, HashSet<String> options){
        this.books = books;
        this.options = options;
    }

    public String welcome(){
        return welcomeMessage;
    }

    public String listBooks(){

        String message = "";
        for(Book book: books){
            message += book.printDetails() + "\n";
        }
        return message;

    }

    public String viewMenu(){
        return "List Books";
    }

    public Boolean validOption(String option){
        return options.contains(option);
    }

    public ArrayList<Book> getBooks(){
        return books;
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

}
