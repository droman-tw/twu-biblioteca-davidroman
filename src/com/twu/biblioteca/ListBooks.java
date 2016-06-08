package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by droman on 6/7/16.
 */
public class ListBooks implements Option {

    public String executeOption(BibliotecaApp biblioteca){
        String message = "";
        ArrayList<Book> books = biblioteca.getBooks();

        for(Book book: books){
            message += book.printDetails() + "\n";
        }
        return message;
    }

}
