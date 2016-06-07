package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    
}
