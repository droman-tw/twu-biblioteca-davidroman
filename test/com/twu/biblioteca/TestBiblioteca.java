package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/6/16.
 */
public class TestBiblioteca {

    @Test
    public void testWelcome(){
        BibliotecaApp biblioteca = new BibliotecaApp();
        assertEquals(biblioteca.welcome(), "Welcome to the Bangalore Public Library!");
    }

    @Test
    public void testBookDetails(){
        String title = "Relato de un Naufrago";
        String author = "Gabriel Garcia Marquez";
        int yearPublished = 1970;
        Book book = new Book(title, author, yearPublished);
        assertEquals(book.title, title);
        assertEquals(book.author, author);
        assertEquals(book.yearPublished, yearPublished);
    }

    @Test
    public void testBookEquality(){
        assertTrue(new Book("Hobbit", "JRR Tolkien", 1937).equals(new Book("Hobbit", "JRR Tolkien", 1937)));
        assertFalse(new Book("Hobbit", "JRR Tolkien", 1937).equals(new Book("Relato de un Naufrago", "Gabriel Garcia Marquez", 1970)));
    }

    @Test
    public void testPrintDetails(){
        Book book = new Book("Hobbit", "JRR Tolkien", 1937);
        assertEquals(book.printDetails(), "Hobbit, JRR Tolkien, 1937");
    }

    @Test
    public void testListBooks(){
        BibliotecaApp biblioteca = new BibliotecaApp();
        
    }
    
}
