package com.twu.biblioteca;


/**
 * Created by droman on 6/6/16.
 */
public class Book {

    public String title;
    public String author;
    public int yearPublished;
    public Availability status;

    public Book(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.status = Availability.AVAILABLE;
    }


    @Override
    public boolean equals(Object obj){

        Book bookToCompare = (Book) obj;

        if(bookToCompare == null){
            return false;
        }

        if(this.title == bookToCompare.title && this.author == bookToCompare.author
                && this.yearPublished == bookToCompare.yearPublished){
            return true;
        }
        return false;
    }


    public String printDetails(){
        return this.title + ", " + this.author + ", " + this.yearPublished ;
    }

    public Availability getAvailability(){
        return status;
    }


    //This method changes the status of availability (AVAILABLE or UNAVAILABLE)
    public void changeStatus(Availability newStatus){
        this.status = newStatus;
    }



}
