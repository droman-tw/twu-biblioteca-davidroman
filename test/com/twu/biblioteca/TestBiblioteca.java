package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/6/16.
 */
public class TestBiblioteca {

    private BibliotecaApp application;
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

        application = new BibliotecaApp(options, library);





    }

    @Test
    public void shouldReturnWelcomeMessage(){
        assertEquals("Welcome to the Online Bangalore Public Library!", application.welcome());
    }

    @Test
    public void shouldPrintListOfOneBook(){
        LinkedHashMap<Book, Availability> books = new LinkedHashMap<Book, Availability>();

        books.put(hobbitBook, Availability.AVAILABLE);

        Library oneBook = new Library(books);

        BibliotecaApp libraryOneBook = new BibliotecaApp(options, oneBook);
        String expectedMessage = "Title, Author, Year Published\n"+
                                 "Hobbit, JRR Tolkien, 1937\n";

        String actualMessage = libraryOneBook.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void shouldPrintListOfVariousBooks(){

        String expectedMessage = "Title, Author, Year Published\n" +
                                 "Hobbit, JRR Tolkien, 1937\n" +
                                 "Relato de un Naufrago, Gabriel Garcia Marquez, 1970\n" +
                                 "Matilda, Roal Dahl, 1988\n";

        String actualMessage = application.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }



    @Test
    public void shouldNotPrintABookUnavailable(){

        String expectedMessage = "Title, Author, Year Published\n" +
                                 "Relato de un Naufrago, Gabriel Garcia Marquez, 1970\n" +
                                 "Matilda, Roal Dahl, 1988\n";

        library.changeStatus(hobbitBook, Availability.UNAVAILABLE);

        String actualMessage = application.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void shouldPrintTheMenuOfOptions(){
        assertEquals("List Books\n", application.viewMenu());
    }

    @Test
    public void shouldTestValidOptionWasSelected(){
        assertTrue(application.validOption("List Books"));
    }

    @Test
    public void shouldTestInvalidOptionWasSelected(){
        assertFalse(application.validOption("Log Out"));
    }


    @Test
    public void shouldPrintErrorMessageForInvalidOption(){
        String expectedMessage = "Select a valid option!";

        String actualMessage = application.pickOption("Add a Book");

        assertEquals(expectedMessage, actualMessage);
    }

}
