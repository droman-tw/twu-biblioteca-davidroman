package com.twu.biblioteca;

import java.util.LinkedHashMap;

/**
 * Created by droman on 6/17/16.
 */
public class ListCheckoutsOption implements Option {
    public String executeOption(Application application){
        Library library = application.getLibrary();
        LinkedHashMap<Item, User> recordCheckouts = library.getCheckoutRecords();

        String message = "Item Name, User\n";

        for(Item item : recordCheckouts.keySet()){

            User userOfCheckout = recordCheckouts.get(item);

            if(item instanceof Book){
                message += ((Book) item).getTitle();
            }
            else if(item instanceof Movie){
                message += ((Movie) item).getName();
            }
            message += ", " + userOfCheckout.getPersonalInformation().getName() + "\n";
        }

        return message;
    }
}
