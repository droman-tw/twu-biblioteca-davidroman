package com.twu.biblioteca;

/**
 * Created by droman on 6/15/16.
 */
public class PersonInfo {

    private String name;
    private String email;
    private String phone;

    public PersonInfo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String printPersonalInformation() {
        String info = "Name: " + this.name + "\n";
        info += "Email: " + this.email + "\n";
        info += "Phone: " + this.phone + "\n";

        return info;
    }

    public boolean isNameEqual(PersonInfo infoToBeChecked) {
        return (this.name).equals(infoToBeChecked.name);
    }

    public boolean isEmailEqual(PersonInfo infoToBeChecked) {
        return (this.email).equals(infoToBeChecked.email);
    }

    public boolean isPhoneEqual(PersonInfo infoToBeChecked) {
        return (this.phone).equals(infoToBeChecked.phone);
    }

    public boolean isPersonalInfoEqual(PersonInfo infoToBeChecked) {
        return isEmailEqual(infoToBeChecked) && isNameEqual(infoToBeChecked) && isPhoneEqual(infoToBeChecked);
    }

    @Override
    public boolean equals(Object object){
        PersonInfo infoToBeChecked = (PersonInfo) object;
        return isPersonalInfoEqual(infoToBeChecked);
    }
}
