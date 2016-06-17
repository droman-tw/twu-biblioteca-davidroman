package com.twu.biblioteca;

import java.util.*;

public class Application {

    private String welcomeMessage = "Welcome to the Online Bangalore Public Library!";
    private HashMap<String, Option> options;
    private Library library;
    private HashMap<String, User> users;
    private User currentUser;


    public Application(HashMap<String, Option> options, Library library, HashMap<String, User> users){
        this.options = options;
        this.library = library;
        this.users = users;
    }

    public String welcome(){
        return welcomeMessage;
    }


    public String pickOption(String optionName){
        if(validOption(optionName)) {
            Option option = options.get(optionName);
            return option.executeOption(this);
        }
        return "Select a valid option!";
    }

    public void addOption(String optionName, Option option){
        options.put(optionName, option);
    }

    public String viewMenu(){
        String menuMessage = "";

        for(String option : options.keySet()){
            menuMessage += option + "\n";
        }

        return menuMessage;
    }

    public Boolean validOption(String option){

        return options.containsKey(option);
    }


    public Boolean isValidUserID(String userID){
        return users.containsKey(userID);
    }


    public Boolean login(String userID, String password){
        if(isValidUserID(userID)){

            User userToBeChecked = users.get(userID);

            if(userToBeChecked.checkPassword(password)) {
                this.currentUser = userToBeChecked;
                return true;
            }
        }

        return false;
    }

    public Library getLibrary(){
        return this.library;
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public String checkoutFromUser(Item item){
        return library.checkOut(item, this.currentUser);
    }

    public String returnFromUser(Item item) {
        return library.returnItem(item);
    }


}
