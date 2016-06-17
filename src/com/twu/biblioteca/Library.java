package com.twu.biblioteca;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Created by droman on 6/15/16.
 */
public class Library {

    private LinkedHashSet<Item> items;
    private HashMap<Item, User> checkoutRecords;


    public Library(LinkedHashSet<Item> items){

        this.items = items;

        this.checkoutRecords = new HashMap<Item, User>();
    }

    public LinkedHashSet<Item> getItems(){

        return items;
    }

    public HashMap<Item, User> getCheckoutRecords(){
        return checkoutRecords;
    }

    public void recordCheckout(Item item, User user){
        checkoutRecords.put(item, user);
    }


    public Boolean isItemInLibrary(Item item){
        return items.contains(item);
    }


    public Boolean isItemAvailable(Item item){
        if(isItemInLibrary(item)) {
            return !(checkoutRecords.containsKey(item));
        }

        return false;
    }

    public Item findItem(Item itemToFind){

        if(isItemInLibrary(itemToFind)){
            for(Item item : items){
                if(item.equals(itemToFind)){
                    return item;
                }
            }
        }
        return null;
    }

    public String checkOut(Item itemToCheckout, User user){

        Item itemFound = findItem(itemToCheckout);

        if(isItemAvailable(itemFound)){
            recordCheckout(itemToCheckout, user);
            return "Thank you! Enjoy the item!";
        }
        return "That item is not available";
    }

    public String returnItem(Item item){
        if(!isItemAvailable(item) && isItemInLibrary(item)){
            checkoutRecords.remove(item);
            return "Thank you for returning the item";
        }

        return "That is not a valid item to return";
    }

}
