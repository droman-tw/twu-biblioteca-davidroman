package com.twu.biblioteca;


/**
 * Created by droman on 6/6/16.
 */
public class Book {

    public String title;
    public String author;
    public int yearPublished;
    //public Availability status;

    public Book(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        //this.status = Availability.AVAILABLE;
    }


    @Override
    public boolean equals(Object obj){

        Book bookToCompare = (Book) obj;

        if(bookToCompare == null){
            if(this == null){
                return true;
            }
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

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + yearPublished;
        return result;
    }
}
