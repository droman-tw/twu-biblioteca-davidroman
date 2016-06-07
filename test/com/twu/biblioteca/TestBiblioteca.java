package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    private ArrayList<Book> library;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
        matildaBook = new Book("Matilda", "Roal Dahl", 1988);

        library = new ArrayList<Book>();
        library.add(hobbitBook);
        library.add(marquezBook);
        library.add(matildaBook);
        biblioteca = new BibliotecaApp(library);
    }

    @Test
    public void testWelcome(){
        BibliotecaApp biblioteca = new BibliotecaApp(null);
        assertEquals("Welcome to the Bangalore Public Library!", biblioteca.welcome());
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
    public void testPrintDetails(){
        assertEquals("Hobbit, JRR Tolkien, 1937", hobbitBook.printDetails());
    }

    @Test
    public void testListOneBook(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(hobbitBook);
        BibliotecaApp libraryOneBook = new BibliotecaApp(books);
        String message = "Hobbit, JRR Tolkien, 1937\n";

        assertEquals(message, libraryOneBook.listBooks());
    }


    @Test
    public void testListVariousBooks(){

        String message = "Hobbit, JRR Tolkien, 1937\n" +
                        "Relato de un Naufrago, Gabriel Garcia Marquez, 1970\n" +
                        "Matilda, Roal Dahl, 1988\n";

        assertEquals(message, biblioteca.listBooks());
    }

    @Test
    public void testViewMenu(){
        assertEquals("List Books", biblioteca.viewMenu());
    }

    @Test
    public void testSelectingValidOption(){
        assertTrue(BibliotecaApp.pickOption("List Books"));
    }



}
