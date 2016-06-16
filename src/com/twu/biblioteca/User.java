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
        userDetails += "User ID: "+this.userID + "\n";
        return userDetails;
    }

    public boolean checkPassword(String possiblePassword){
        return password.equals(possiblePassword);
    }

    public boolean isPersonalInfoOfUsersEqual(User userCompared){
        return (this.personalInformation).equals(userCompared.personalInformation);
    }

    public boolean isUserIDEqual(User userCompared){
        return (this.userID).equals(userCompared.userID);
    }

    public boolean isPasswordEqual(User userCompared) {
        return (this.password).equals(userCompared.password);
    }


    @Override
    public boolean equals(Object object){
        User userCompared = (User) object;

        return isPersonalInfoOfUsersEqual(userCompared) && isUserIDEqual(userCompared) && isPasswordEqual(userCompared);

    }

}
