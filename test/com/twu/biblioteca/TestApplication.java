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
public class TestApplication {

    private Application application;
    private Library library;
    private Book hobbitBook;
    private Book marquezBook;
    private Book matildaBook;
    private User david;
    private User larry;
    private LinkedHashMap<Item, Availability> listBooks;
    HashMap<String, User> users;
    private HashMap<String, Option> options;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        listBooks = new LinkedHashMap<Item, Availability>();
        listBooks.put(hobbitBook, Availability.AVAILABLE);
        listBooks.put(marquezBook, Availability.AVAILABLE);
        listBooks.put(matildaBook, Availability.AVAILABLE);

        options = new HashMap<String, Option>();

        Option optionListBooks = new ListBooks();
        Option optionGetUserDetails = new UserDetailsOption();

        options.put("List Books", optionListBooks);

        options.put("User Details", optionGetUserDetails);

        PersonInfo davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");

        david = new User(davidInfo, "111-1111", "biblioteca1");

        PersonInfo larryInfo = new PersonInfo("Larry Roman", "lroman@gmail.com", "0999353546");

        larry = new User(larryInfo, "222-2222", "biblioteca2");

        users = new HashMap<String, User>();

        users.put(david.getUserID(), david);

        users.put(larry.getUserID(), larry);


        library = new Library(listBooks);

        application = new Application(options, library, users);

    }

    @Test
    public void shouldReturnWelcomeMessage(){
        assertEquals("Welcome to the Online Bangalore Public Library!", application.welcome());
    }

    @Test
    public void shouldPrintListOfOneBook(){
        LinkedHashMap<Item, Availability> books = new LinkedHashMap<Item, Availability>();

        books.put(hobbitBook, Availability.AVAILABLE);

        Library oneBook = new Library(books);

        Application libraryOneBook = new Application(options, oneBook, users);
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
        assertEquals("List Books\nUser Details\n", application.viewMenu());
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


    @Test
    public void shouldTestValidUser(){
        assertTrue(application.isValidUserID("111-1111"));
    }

    @Test
    public void shouldTestInvalidUser(){
        assertFalse(application.isValidUserID("121-1111"));
    }

    @Test
    public void shouldLoginSuccesfullyWithValidUserID(){

        assertTrue(application.login("111-1111", "biblioteca1"));
        assertEquals(david, application.getCurrentUser());
    }


    @Test
    public void shouldExecuteUserDetailsOptionCorreclty(){
        application.login("111-1111", "biblioteca1");

        String expectedMessage = "Name: David Roman\n" +
                                 "Email: droman@thoughtworks.com\n" +
                                 "Phone: 0939053446\n" +
                                 "User ID: 111-1111\n";

        String actualMessage = application.pickOption("User Details");

        assertEquals(expectedMessage, actualMessage);
    }

}
