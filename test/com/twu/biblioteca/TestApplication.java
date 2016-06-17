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

    private Item hobbitBook;
    private Item marquezBook;
    private Item matildaBook;

    private Item schoolRock;
    private Item pulpFiction;
    private Item gladiator;

    private User david;
    private User larry;

    private LinkedHashMap<Item, Availability> listItems;

    HashMap<String, User> users;

    private HashMap<String, Option> options;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        schoolRock = new Movie("School of Rock", 2003, "Richard Linklater", 0);
        pulpFiction = new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 10);
        gladiator = new Movie("Gladiator", 2000, "Ridley Scott", 8);

        listItems = new LinkedHashMap<Item, Availability>();

        listItems.put(hobbitBook, Availability.AVAILABLE);
        listItems.put(marquezBook, Availability.AVAILABLE);
        listItems.put(matildaBook, Availability.AVAILABLE);

        listItems.put(schoolRock, Availability.AVAILABLE);
        listItems.put(pulpFiction, Availability.AVAILABLE);
        listItems.put(gladiator, Availability.AVAILABLE);

        options = new HashMap<String, Option>();

        Option optionListBooks = new ListBooksOption();
        Option optionListMovies = new ListMoviesOption();
        Option optionGetUserDetails = new UserDetailsOption();

        options.put("List Books", optionListBooks);

        options.put("List Movies", optionListMovies);

        options.put("User Details", optionGetUserDetails);

        PersonInfo davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");

        david = new User(davidInfo, "111-1111", "biblioteca1");

        PersonInfo larryInfo = new PersonInfo("Larry Roman", "lroman@gmail.com", "0999353546");

        larry = new User(larryInfo, "222-2222", "biblioteca2");

        users = new HashMap<String, User>();

        users.put(david.getUserID(), david);

        users.put(larry.getUserID(), larry);


        library = new Library(listItems);

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

        Application applicationOneBook = new Application(options, oneBook, users);

        String expectedMessage = "Title, Author, Year Published\n"+
                                 "Hobbit, JRR Tolkien, 1937\n";

        String actualMessage = applicationOneBook.pickOption("List Books");

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
        assertEquals("List Books\nList Movies\nUser Details\n", application.viewMenu());
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

    @Test
    public void shouldPrintListOfOneMovie(){
        LinkedHashMap<Item, Availability> movies = new LinkedHashMap<Item, Availability>();

        movies.put(pulpFiction, Availability.AVAILABLE);

        Library oneMovie = new Library(movies);

        Application applicationOneMovie = new Application(options, oneMovie, users);

        String expectedMesage = "Name (Year), Director, Rating\n"+
                                "Pulp Fiction (1994), Quentin Tarantino, 10 stars\n";

        String actualMessage = applicationOneMovie.pickOption("List Movies");

        assertEquals(expectedMesage, actualMessage);

    }

    @Test
    public void shouldPrintListOfVariousMovies(){
        String expectedMesage = "Name (Year), Director, Rating\n" +
                                "School of Rock (2003), Richard Linklater, unrated\n" +
                                "Pulp Fiction (1994), Quentin Tarantino, 10 stars\n" +
                                "Gladiator (2000), Ridley Scott, 8 stars\n";

        String actualMessage = application.pickOption("List Movies");

        assertEquals(expectedMesage, actualMessage);

    }

}
