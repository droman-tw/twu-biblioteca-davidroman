package com.twu.biblioteca;

import java.util.Set;

/**
 * Created by droman on 6/16/16.
 */
public class ListMoviesOption implements Option {

    public String executeOption(Application application){
        Library library = application.getLibrary();

        String message = "Name (Year), Director, Rating\n";
        Set<Item> items = library.getItems().keySet();

        for(Item item: items){
            if(library.isItemAvailable(item) && item instanceof Movie){
                message += item.printDetails() + "\n";
            }
        }
        return message;
    }

}
