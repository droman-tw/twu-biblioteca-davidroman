package com.twu.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by droman on 6/15/16.
 */
public class Library {

    private LinkedHashMap<Item, Availability> items;
    //private HashMap<Item, User> checkoutRecords;


    public Library(LinkedHashMap<Item, Availability> items){

        this.items = items;

        //this.checkoutRecords = new
    }

    public LinkedHashMap<Item, Availability> getItems(){

        return items;
    }


    public Boolean isItemInLibrary(Item item){
        return items.containsKey(item);
    }


    public Availability getAvailability(Item item){
        if(isItemInLibrary(item)){
            return items.get(item);
        }
        return Availability.UNAVAILABLE;
    }

    public void changeStatus(Item item, Availability newStatus){
        if(isItemInLibrary(item)){
            items.put(item, newStatus);
        }
    }

    public Boolean isItemAvailable(Item item){

        if(isItemInLibrary(item)) {
            Availability status = getAvailability(item);
            if (status == Availability.AVAILABLE) {
                return true;
            }
        }

        return false;
    }

    public Item findItem(Item itemToFind){

        if(isItemInLibrary(itemToFind)){
            for(Item item : items.keySet()){
                if(item.equals(itemToFind)){
                    return item;
                }
            }
        }
        return null;
    }

    public String checkOut(Item itemToCheckout){

        Item itemFound = findItem(itemToCheckout);

        if(isItemAvailable(itemFound)){
            changeStatus(itemToCheckout, Availability.UNAVAILABLE);
            return "Thank you! Enjoy the item!";
        }
        return "That item is not available";
    }

    public String returnItem(Item item){
        if(!isItemAvailable(item) && isItemInLibrary(item)){
            changeStatus(item, Availability.AVAILABLE);
            return "Thank you for returning the item";
        }

        return "That is not a valid item to return";
    }

}
