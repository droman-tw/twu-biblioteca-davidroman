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

    public Boolean areDetailsEqual(Book bookToCompare){
        return (this.title).equals(bookToCompare.title) && (this.author).equals(bookToCompare.author)
                && this.yearPublished == bookToCompare.yearPublished;
    }

    @Override
    public boolean equals(Object obj){

        Book bookToCompare = (Book) obj;

        if(bookToCompare == null){
            return false;
        }

        return areDetailsEqual(bookToCompare);
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
