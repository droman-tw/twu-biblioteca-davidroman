package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;

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

    private Item schoolRock;
    private Item pulpFiction;
    private Item gladiator;

    private LinkedHashSet<Item> listItems;

    private User david;

    @Before
    public void setUp() {

        PersonInfo davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");
        david = new User(davidInfo, "111-1111", "biblioteca1");

        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        schoolRock = new Movie("School of Rock", 2003, "Richard Linklater", 0);
        pulpFiction = new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 10);
        gladiator = new Movie("Gladiator", 2000, "Ridley Scott", 8);

        listItems = new LinkedHashSet<Item>();

        listItems.add(hobbitBook);
        listItems.add(marquezBook);
        listItems.add(matildaBook);

        listItems.add(schoolRock);
        listItems.add(pulpFiction);
        listItems.add(gladiator);



        library = new Library(listItems);
    }



    @Test
    public void shouldTestBookIsInLibrary(){
        assertTrue(library.isItemInLibrary(hobbitBook));
    }


    @Test
    public void shouldTestThatNullItemIsNotInLibrary(){
        assertFalse(library.isItemInLibrary(null));
    }

    @Test
    public void shouldRecordACheckout(){
        library.recordCheckout(hobbitBook, david);
        HashMap<Item, User> recordsCheckout = library.getCheckoutRecords();
        assertTrue(recordsCheckout.containsKey(hobbitBook));
    }


    @Test
    public void shouldTestBookInLibraryIsAvailable(){
        assertTrue(library.isItemAvailable(hobbitBook));
    }


    @Test
    public void shouldTestNullItemIsNotAvailable(){
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
    public void shouldCheckOutBookInLibrarySuccessfully(){
        String expectedMessage = "Thank you! Enjoy the item!";
        String resultOfCheckOut = library.checkOut(hobbitBook, david);

        assertEquals(expectedMessage, resultOfCheckOut);

        assertFalse(library.isItemAvailable(hobbitBook));
    }


    @Test
    public void shouldCheckOutBookNotInLibraryUnsuccesfully(){
        String expectedMessage = "That item is not available";
        String resultOfCheckOut = library.checkOut(new Book("Mafalda", "Guillermo Suarez", 1937), david);
        assertEquals(expectedMessage, resultOfCheckOut);
    }


    @Test
    public void shouldCheckOutBookNotAvailableUnsucessfully(){
        String expectedMessage = "That item is not available";

        library.recordCheckout(marquezBook, david);

        String resultOfCheckOut = library.checkOut(marquezBook, david);

        assertEquals(expectedMessage, resultOfCheckOut);
    }


    @Test
    public void shouldReturnSuccessfullyBookUnavailable(){
        library.checkOut(hobbitBook, david);

        String expectedResultReturn = "Thank you for returning the item";

        String actualResultReturn = library.returnItem(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);

        assertTrue(library.isItemAvailable(hobbitBook));
    }

    @Test
    public void shouldReturnUnsuccessfullyBookAvailable(){

        String expectedResultReturn = "That is not a valid item to return";

        String actualResultReturn = library.returnItem(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldReturnUnsuccessfullyNullBook(){
        String expectedResultReturn = "That is not a valid item to return";

        String actualResultReturn = library.returnItem(null);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldReturnUnsuccessfullyBookNotInLibrary(){
        String expectedResultReturn = "That is not a valid item to return";

        String actualResultReturn = library.returnItem(new Book("Mafalda", "Guillermo Suarez", 1937));

        assertEquals(expectedResultReturn, actualResultReturn);
    }


    @Test
    public void shouldTestMovieIsInLibrary(){
        assertTrue(library.isItemInLibrary(pulpFiction));
    }


    @Test
    public void shouldTestMovieInLibraryIsAvailable(){
        assertTrue(library.isItemAvailable(schoolRock));
    }

    @Test
    public void shouldTestMovieNotInLibraryIsNotAvailable(){
        assertFalse(library.isItemAvailable(new Movie("The Conjuring", 2013, "James Wan", 9)));
    }

    @Test
    public void shouldTestMovieFound(){
        Item targetMovie = library.findItem(gladiator);
        assertEquals(gladiator, targetMovie);
    }

    @Test
    public void shouldTestMovieNotFound(){
        Item targetMovie = library.findItem(new Movie("The Conjuring", 2013, "James Wan", 9));
        assertEquals(null, targetMovie);
    }


    @Test
    public void shouldCheckOutMovieInLibrarySuccesfully(){
        String expectedMessage = "Thank you! Enjoy the item!";
        String resultOfCheckOut = library.checkOut(gladiator, david);

        assertEquals(expectedMessage, resultOfCheckOut);

        assertFalse(library.isItemAvailable(gladiator));
    }

    @Test
    public void shouldCheckOutMovieNotInLibraryrUnsuccessfully(){
        String expectedMessage = "That item is not available";
        String resultOfCheckOut = library.checkOut(new Movie("The Conjuring", 2013, "James Wan", 9), david);
        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void shouldCheckOutMovieNotAvailableUnsuccessfully(){
        String expectedMessage = "That item is not available";

        library.recordCheckout(schoolRock, david);

        String resultOfCheckOut = library.checkOut(schoolRock, david);

        assertEquals(expectedMessage, resultOfCheckOut);
    }

}
