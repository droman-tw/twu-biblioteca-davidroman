package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/15/16.
 */
public class TestBook {

    private Item hobbitBook;
    private Item marquezBook;

    @Before
    public void setUp() {
        hobbitBook = new Book("Hobbit", "JRR Tolkien", 1937);
        marquezBook = new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970);
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
    public void shouldTestInequalityWhenComparedToOtherObject(){
        assertFalse(hobbitBook.equals("hello"));
    }

    @Test
    public void shouldReturnNotEqualWhenBookNull(){
        Book bookNull = null;
        assertFalse(hobbitBook.equals(bookNull));
    }

    @Test
    public void shouldPrintBookDetails(){
        assertEquals("Relato de un Naufrago, Gabriel Garcia Marquez, 1970", marquezBook.printDetails());
    }


}
