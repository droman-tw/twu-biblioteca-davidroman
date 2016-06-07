package com.twu.biblioteca;

/**
 * Created by droman on 6/6/16.
 */
public class Book {

    public String title;
    public String author;
    public int yearPublished;

    public Book(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public boolean equals(Book book){
        if(this.title == book.title && this.author == book.author && this.yearPublished == this.yearPublished){
            return true;
        }
        return false;
    }

    public String printDetails(){
        return this.title + ", " + this.author + ", " + this.yearPublished ;
    }

}
