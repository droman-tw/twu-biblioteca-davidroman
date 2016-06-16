package com.twu.biblioteca;

import java.util.LinkedHashMap;

/**
 * Created by droman on 6/15/16.
 */
public class Library {

    private LinkedHashMap<Book, Availability> books;

    public Library(LinkedHashMap<Book, Availability> books){
        this.books = books;
    }

    public LinkedHashMap<Book, Availability> getBooks(){

        return books;
    }

    public Boolean isBookInLibrary(Book book){
        return books.containsKey(book);
    }


    public Availability getAvailability(Book book){
        if(isBookInLibrary(book)){
            return books.get(book);
        }
        return Availability.UNAVAILABLE;
    }

    public void changeStatus(Book book, Availability newStatus){
        if(isBookInLibrary(book)){
            books.put(book, newStatus);
        }
    }

    public Boolean isBookAvailable(Book book){

        if(isBookInLibrary(book)) {
            Availability status = getAvailability(book);
            if (status == Availability.AVAILABLE) {
                return true;
            }
        }

        return false;
    }

    public Book findBook(Book bookToFind){

        if(isBookInLibrary(bookToFind)){
            for(Book book : books.keySet()){
                if(book.equals(bookToFind)){
                    return book;
                }
            }
        }
        return null;
    }

    public String checkOut(Book bookToCheckout){

        Book bookFound = findBook(bookToCheckout);

        if(isBookAvailable(bookFound)){
            changeStatus(bookToCheckout, Availability.UNAVAILABLE);
            return "Thank you! Enjoy the book!";
        }
        return "That book is not available";
    }

    public String returnBook(Book book){
        if(!isBookAvailable(book) && isBookInLibrary(book)){
            changeStatus(book, Availability.AVAILABLE);
            return "Thank you for returning the book";
        }

        return "That is not a valid book to return";
    }

}
