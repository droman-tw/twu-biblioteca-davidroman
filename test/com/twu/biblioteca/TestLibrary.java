package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/15/16.
 */
public class TestLibrary {

    private Library library;
    private Book hobbitBook;
    private Book marquezBook;
    private Book matildaBook;
    private LinkedHashMap<Book, Availability> listBooks;
    //private HashMap<String, User> users;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        listBooks = new LinkedHashMap<Book, Availability>();
        listBooks.put(hobbitBook, Availability.AVAILABLE);
        listBooks.put(marquezBook, Availability.AVAILABLE);
        listBooks.put(matildaBook, Availability.AVAILABLE);
        /*
        PersonInfo davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");

        User david = new User(davidInfo, "111-1111", "biblioteca1");

        PersonInfo larryInfo = new PersonInfo("Larry Roman", "lroman@gmail.com", "0999353546");

        User larry = new User(larryInfo, "222-2222", "biblioteca2");

        users = new HashMap<String, User>();

        users.put(david.getUserID(), david);

        users.put(larry.getUserID(), larry);
        */

        library = new Library(listBooks);
    }


    @Test
    public void shouldTestIfBookIsInLibrary(){
        assertTrue(library.isBookInLibrary(hobbitBook));
    }

    @Test
    public void shouldTestThatNullBookIsNotInLibrary(){
        assertFalse(library.isBookInLibrary(null));
    }

    @Test
    public void shouldTestNullBookIsNotAvailable(){
        assertFalse(library.isBookAvailable(null));
    }

    @Test
    public void shouldTestBookNotInLibraryIsNotAvailable(){
        assertFalse(library.isBookAvailable(new Book("Mafalda", "Guillermo Suarez", 1937)));

    }

    @Test
    public void shouldTestBookFound(){
        Book targetBook = library.findBook(hobbitBook);
        assertEquals(hobbitBook, targetBook);
    }

    @Test
    public void shouldTestBookNotFound(){
        Book targetBook = library.findBook(new Book("Mafalda", "Guillermo Suarez", 1937));
        assertEquals(null, targetBook);
    }

    @Test
    public void shouldChangeOfBookStatusToUnavailable(){
        library.changeStatus(hobbitBook, Availability.UNAVAILABLE);
        assertFalse(library.isBookAvailable(hobbitBook));
    }

    @Test
    public void shouldCheckOutBookInLibrarySuccessfully(){
        String expectedMessage = "Thank you! Enjoy the book!";
        String resultOfCheckOut = library.checkOut(hobbitBook);

        assertEquals(expectedMessage, resultOfCheckOut);

        assertFalse(library.isBookAvailable(hobbitBook));
    }

    @Test
    public void shouldCheckOutBookNotInLibraryUnsuccesfully(){
        String expectedMessage = "That book is not available";
        String resultOfCheckOut = library.checkOut(new Book("Mafalda", "Guillermo Suarez", 1937));
        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void shouldCheckOutBookNotAvailableUnsucessfully(){
        String expectedMessage = "That book is not available";

        library.changeStatus(marquezBook, Availability.UNAVAILABLE);

        String resultOfCheckOut = library.checkOut(marquezBook);

        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void shouldReturnSuccessFullyBookUnavailable(){
        library.checkOut(hobbitBook);

        String expectedResultReturn = "Thank you for returning the book";

        String actualResultReturn = library.returnBook(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);

        assertTrue(library.isBookAvailable(hobbitBook));
    }

    @Test
    public void shouldReturnUnsuccessfullyBookAvailable(){

        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnBook(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldTestBookIsAvailable(){
        assertTrue(library.isBookAvailable(hobbitBook));
    }


    @Test
    public void shouldReturnUnsuccessfullyNullBook(){
        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnBook(null);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldReturnUnsuccessfullyBookNotInLibrary(){
        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnBook(new Book("Mafalda", "Guillermo Suarez", 1937));

        assertEquals(expectedResultReturn, actualResultReturn);
    }


}
