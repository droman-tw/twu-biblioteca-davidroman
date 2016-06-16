package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by droman on 6/7/16.
 */
public class ListBooks implements Option {

    public String executeOption(Application application){
        Library library = application.getLibrary();

        String message = "Title, Author, Year Published\n";
        Set<Item> books = library.getBooks().keySet();

        for(Item book: books){
            if(library.isItemAvailable(book)){
                message += book.printDetails() + "\n";
            }
        }
        return message;
    }

}
