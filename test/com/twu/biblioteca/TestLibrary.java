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

    private BibliotecaApp biblioteca;
    private Library library;
    private Book hobbitBook;
    private Book marquezBook;
    private Book matildaBook;
    private LinkedHashMap<Book, Availability> listBooks;
    private HashMap<String, Option> options;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        listBooks = new LinkedHashMap<Book, Availability>();
        listBooks.put(hobbitBook, Availability.AVAILABLE);
        listBooks.put(marquezBook, Availability.AVAILABLE);
        listBooks.put(matildaBook, Availability.AVAILABLE);

        options = new HashMap<String, Option>();

        Option optionListBooks = new ListBooks();

        options.put("List Books", optionListBooks);

        library = new Library(listBooks);

        biblioteca = new BibliotecaApp(options, library);
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

        //Assert ending result of the action
        assertEquals(expectedMessage, resultOfCheckOut);

        //This assertion is to make sure that the status of the object has changed succesfully
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

        //Change the status of a book to prepare test
        library.changeStatus(marquezBook, Availability.UNAVAILABLE);

        String resultOfCheckOut = library.checkOut(marquezBook);

        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void shouldReturnSuccessFullyBookUnavailable(){
        library.checkOut(hobbitBook);

        String expectedResultReturn = "Thank you for returning the book";

        String actualResultReturn = library.returnBook(hobbitBook);

        //Assert the correct message
        assertEquals(expectedResultReturn, actualResultReturn);

        //Assert that hobbit has change its status from Available to Unavailable
        assertTrue(library.isBookAvailable(hobbitBook));
    }

    @Test
    public void shouldReturnUnsuccessfullyBookAvailable(){

        String expectedResultReturn = "That is not a valid book to return";

        //Hobbit is Available by default. The return wont be possible
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

        //Hobbit is Available by default. The return wont be possible
        String actualResultReturn = library.returnBook(null);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void shouldReturnUnsuccessfullyBookNotInLibrary(){
        String expectedResultReturn = "That is not a valid book to return";

        //Hobbit is Available by default. The return wont be possible
        String actualResultReturn = library.returnBook(new Book("Mafalda", "Guillermo Suarez", 1937));

        assertEquals(expectedResultReturn, actualResultReturn);
    }


}
