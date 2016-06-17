package com.twu.biblioteca;


/**
 * Created by droman on 6/6/16.
 */
public class Book implements Item{

    private String title;
    private String author;
    private int yearPublished;

    public int getYearPublished() {
        return yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

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

        if(!(obj instanceof Book) || obj == null){
            return false;
        }

        Book bookToCompare = (Book) obj;


        return areDetailsEqual(bookToCompare);
    }


    @Override
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
