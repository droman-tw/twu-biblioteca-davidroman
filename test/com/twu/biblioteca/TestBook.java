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
public class TestBook {

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

    }

    @Test
    public void shouldTestACorrectInitializationOfBookDetails(){
        String title = "Relato de un Naufrago";
        String author = "Gabriel Garcia Marquez";
        int yearPublished = 1970;
        Book book = new Book(title, author, yearPublished);
        assertEquals(title, book.title);
        assertEquals(author, book.author);
        assertEquals(yearPublished, book.yearPublished);
    }

    @Test
    public void shouldTestIfTwoBooksAreEqual(){
        assertTrue(hobbitBook.equals(new Book("Hobbit", "JRR Tolkien", 1937)));
        assertFalse(hobbitBook.equals(new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970)));
    }

    @Test
    public void shouldReturnNotEqualWhenBookNull(){
        Book bookNull = null;
        assertFalse(hobbitBook.equals(bookNull));
    }

    @Test
    public void shouldPrintBookDetails(){
        assertEquals("Hobbit, JRR Tolkien, 1937", hobbitBook.printDetails());
    }


}
