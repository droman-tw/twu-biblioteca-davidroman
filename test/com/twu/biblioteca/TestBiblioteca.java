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

    private BibliotecaApp biblioteca;
    private Book hobbitBook;
    private Book marquezBook;
    private Book matildaBook;
    private LinkedHashMap<Book, Availability> library;
    private HashMap<String, Option> options;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        library = new LinkedHashMap<Book, Availability>();
        library.put(hobbitBook, Availability.AVAILABLE);
        library.put(marquezBook, Availability.AVAILABLE);
        library.put(matildaBook, Availability.AVAILABLE);

        options = new HashMap<String, Option>();

        Option optionListBooks = new ListBooks();

        options.put("List Books", optionListBooks);

        biblioteca = new BibliotecaApp(library, options);

    }

    @Test
    public void testWelcome(){
        assertEquals("Welcome to the Online Bangalore Public Library!", biblioteca.welcome());
    }

    @Test
    public void testBookDetails(){
        String title = "Relato de un Naufrago";
        String author = "Gabriel Garcia Marquez";
        int yearPublished = 1970;
        Book book = new Book(title, author, yearPublished);
        assertEquals(title, book.title);
        assertEquals(author, book.author);
        assertEquals(yearPublished, book.yearPublished);
    }

    @Test
    public void testBookEquality(){
        assertTrue(hobbitBook.equals(hobbitBook));
        assertFalse(hobbitBook.equals(marquezBook));
    }

    @Test
    public void testBookNullEquality(){
        Book bookNull = null;
        assertFalse(hobbitBook.equals(bookNull));
    }

    @Test
    public void testPrintDetails(){
        assertEquals("Hobbit, JRR Tolkien, 1937", hobbitBook.printDetails());
    }

    @Test
    public void testListOneBook(){
        LinkedHashMap<Book, Availability> books = new LinkedHashMap<Book, Availability>();
        books.put(hobbitBook, Availability.AVAILABLE);
        BibliotecaApp libraryOneBook = new BibliotecaApp(books, options);
        String expectedMessage = "Title, Author, Year Published\n"+
                                 "Hobbit, JRR Tolkien, 1937\n";

        String actualMessage = libraryOneBook.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testListVariousBooks(){

        String expectedMessage = "Title, Author, Year Published\n" +
                                 "Hobbit, JRR Tolkien, 1937\n" +
                                 "Relato de un Naufrago, Gabriel Garcia Marquez, 1970\n" +
                                 "Matilda, Roal Dahl, 1988\n";

        String actualMessage = biblioteca.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testBookIsInLibrary(){
        assertTrue(biblioteca.isBookInLibrary(hobbitBook));
    }

    @Test
    public void testNullBookIsInLibrary(){
        assertFalse(biblioteca.isBookInLibrary(null));
    }

    @Test
    public void testListOneBookUnavailable(){

        String expectedMessage = "Title, Author, Year Published\n" +
                                 "Relato de un Naufrago, Gabriel Garcia Marquez, 1970\n" +
                                 "Matilda, Roal Dahl, 1988\n";

        biblioteca.changeStatus(hobbitBook, Availability.UNAVAILABLE);

        String actualMessage = biblioteca.pickOption("List Books");

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testViewMenu(){
        assertEquals("List Books\n", biblioteca.viewMenu());
    }

    @Test
    public void testSelectingValidOption(){
        assertTrue(biblioteca.validOption("List Books"));
    }

    @Test
    public void testSelectingInvalidOption(){
        assertFalse(biblioteca.validOption("Log Out"));
    }


    @Test
    public void testActionInvalidOption(){
        String expectedMessage = "Select a valid option!";

        String actualMessage =  biblioteca.pickOption("Add a Book");

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCheckingBookAvailability(){
        assertTrue(biblioteca.isBookAvailable(hobbitBook));
    }

    @Test
    public void testChekingNullBookAvailability(){
        assertFalse(biblioteca.isBookAvailable(null));
    }

    @Test
    public void testAvailabilityOfBookNotInLibrary(){
        assertFalse(biblioteca.isBookAvailable(new Book("Mafalda", "Guillermo Suarez", 1937)));

    }

    @Test
    public void testFindBook(){
        Book targetBook = biblioteca.findBook(hobbitBook);
        assertEquals(hobbitBook, targetBook);
    }

    @Test
    public void testBookNotFound(){
        Book targetBook = biblioteca.findBook(new Book("Mafalda", "Guillermo Suarez", 1937));
        assertEquals(null, targetBook);
    }

    @Test
    public void testChangeBookStatusToUnavailable(){
        biblioteca.changeStatus(hobbitBook, Availability.UNAVAILABLE);
        assertFalse(biblioteca.isBookAvailable(hobbitBook));
    }

    @Test
    public void testCheckOutBookAvailable(){
        String expectedMessage = "Thank you! Enjoy the book!";
        String resultOfCheckOut = biblioteca.checkOut(hobbitBook);

        //Assert ending result of the action
        assertEquals(expectedMessage, resultOfCheckOut);

        //This assertion is to make sure that the status of the object has changed succesfully
        assertFalse(biblioteca.isBookAvailable(hobbitBook));
    }

    @Test
    public void testCheckOutBookNotInLibrary(){
        String expectedMessage = "That book is not available";
        String resultOfCheckOut = biblioteca.checkOut(new Book("Mafalda", "Guillermo Suarez", 1937));
        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void testCheckOutBookNotAvailable(){
        String expectedMessage = "That book is not available";

        //Change the status of a book to prepare test
        biblioteca.changeStatus(marquezBook, Availability.UNAVAILABLE);

        String resultOfCheckOut = biblioteca.checkOut(marquezBook);

        assertEquals(expectedMessage, resultOfCheckOut);
    }

    @Test
    public void testReturnBookSuccesfully(){
        biblioteca.checkOut(hobbitBook);

        String expectedResultReturn = "Thank you for returning the book";

        String actualResultReturn = biblioteca.returnBook(hobbitBook);

        //Assert the correct message
        assertEquals(expectedResultReturn, actualResultReturn);

        //Assert that hobbit has change its status from Available to Unavailable
        assertTrue(biblioteca.isBookAvailable(hobbitBook));
    }

    @Test
    public void testReturnBookUnsuccesfully(){

        String expectedResultReturn = "That is not a valid book to return";

        //Hobbit is Available by default. The return wont be possible
        String actualResultReturn = biblioteca.returnBook(hobbitBook);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void testReturnNullBook(){
        String expectedResultReturn = "That is not a valid book to return";

        //Hobbit is Available by default. The return wont be possible
        String actualResultReturn = biblioteca.returnBook(null);

        assertEquals(expectedResultReturn, actualResultReturn);
    }

    @Test
    public void testReturnBookNotInLibrary(){
        String expectedResultReturn = "That is not a valid book to return";

        //Hobbit is Available by default. The return wont be possible
        String actualResultReturn = biblioteca.returnBook(new Book("Mafalda", "Guillermo Suarez", 1937));

        assertEquals(expectedResultReturn, actualResultReturn);
    }



}
