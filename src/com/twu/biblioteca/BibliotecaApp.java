package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {

    private String welcomeMessage = "Welcome to the Bangalore Public Library!";
    private ArrayList<Book> books;


    public BibliotecaApp(ArrayList<Book> books){
        this.books = books;
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





    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }

}
