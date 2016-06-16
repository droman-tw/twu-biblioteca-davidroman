package com.twu.biblioteca;

/**
 * Created by droman on 6/15/16.
 */
public class User {

    private PersonInfo personalInformation;
    private String userID;
    private String password;

    public User(PersonInfo personalInformation, String userID, String password) {
        this.personalInformation = personalInformation;
        this.userID = userID;
        this.password = password;
    }

    public PersonInfo getPersonalInformation() {
        return personalInformation;
    }


    public String getUserID() {
        return userID;
    }


    public String getPassword() {
        return password;
    }

    public String printUserDetails(){
        String userDetails = this.personalInformation.printPersonalInformation();
        userDetails += "User ID: "+this.userID;
        return userDetails;
    }

    public Boolean checkPassword(String possiblePassword){
        return password.equals(possiblePassword);
    }

}
