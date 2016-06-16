package com.twu.biblioteca;

import java.util.Set;

/**
 * Created by droman on 6/7/16.
 */
public class ListBooks implements Option {

    public String executeOption(Application application){
        Library library = application.getLibrary();

        String message = "Title, Author, Year Published\n";
        Set<Item> items = library.getItems().keySet();

        for(Item item: items){
            if(library.isItemAvailable(item) && item instanceof Book){
                message += item.printDetails() + "\n";
            }
        }
        return message;
    }

}
