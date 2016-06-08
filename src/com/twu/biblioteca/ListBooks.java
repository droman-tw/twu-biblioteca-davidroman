package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by droman on 6/7/16.
 */
public class ListBooks implements Option {

    public String executeOption(BibliotecaApp biblioteca){
        String message = "Title, Author, Year Published\n";
        Set<Book> books = biblioteca.getBooks().keySet();

        for(Book book: books){
            if(biblioteca.isBookAvailable(book)){
                message += book.printDetails() + "\n";
            }
        }
        return message;
    }

}
