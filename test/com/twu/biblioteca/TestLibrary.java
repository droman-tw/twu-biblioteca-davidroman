package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/15/16.
 */
public class TestLibrary {

    private Library library;
    private Item hobbitBook;
    private Item marquezBook;
    private Item matildaBook;
    private LinkedHashMap<Item, Availability> listBooks;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        listBooks = new LinkedHashMap<Item, Availability>();
        listBooks.put(hobbitBook, Availability.AVAILABLE);
        listBooks.put(marquezBook, Availability.AVAILABLE);
        listBooks.put(matildaBook, Availability.AVAILABLE);


        library = new Library(listBooks);
    }


    @Test
    public void shouldTestIfBookIsInLibrary(){
        assertTrue(library.isItemInLibrary(hobbitBook));
    }

    @Test
    public void shouldTestThatNullBookIsNotInLibrary(){
        assertFalse(library.isItemInLibrary(null));
    }

    @Test
    public void shouldTestNullBookIsNotAvailable(){
        assertFalse(library.isItemAvailable(null));
    }

    @Test
    public void shouldTestBookNotInLibraryIsNotAvailable(){
        assertFalse(library.isItemAvailable(new Book("Mafalda", "Guillermo Suarez", 1937)));

    }

    @Test
    public void shouldTestBookFound(){
        Item targetBook = library.findItem(hobbitBook);
        assertEquals(hobbitBook, targetBook);
    }

    @Test
    public void shouldTestBookNotFound(){
        Item targetBook = library.findItem(new Book("Mafalda", "Guillermo Suarez", 1937));
        assertEquals(null, targetBook);
    }

    @Test
    public void shouldChangeOfBookStatusToUnavailable(){
        library.changeStatus(hobbitBook, Availability.UNAVAILABLE);
        assertFalse(library.isItemAvailable(hobbitBook));
    }

    @Test
    public void shouldCheckOutBookInLibrarySuccessfully(){
        String expectedMessage = "Thank you! Enjoy the book!";
        String resultOfCheckOut = library.checkOut(hobbitBook);

        assertEquals(expectedMessage, resultOfCheckOut);

        assertFalse(library.isItemAvailable(hobbitBook));
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

        String actualResultReturn = library.returnItem(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);

        assertTrue(library.isItemAvailable(hobbitBook));
    }

    @Test
    public void shouldReturnUnsuccessfullyBookAvailable(){

        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnItem(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldTestBookIsAvailable(){
        assertTrue(library.isItemAvailable(hobbitBook));
    }


    @Test
    public void shouldReturnUnsuccessfullyNullBook(){
        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnItem(null);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldReturnUnsuccessfullyBookNotInLibrary(){
        String expectedResultReturn = "That is not a valid book to return";

        String actualResultReturn = library.returnItem(new Book("Mafalda", "Guillermo Suarez", 1937));

        assertEquals(expectedResultReturn, actualResultReturn);
    }


}
