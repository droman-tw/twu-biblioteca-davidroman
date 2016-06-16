package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/16/16.
 */
public class TestMovie {

    private Item schoolRock;

    @Before
    public void setUp(){
        schoolRock = new Movie("School of Rock", 2003, "Richard Linklater", 0);
    }

    @Test
    public void shouldInitializrMovieCorrectly(){
        Movie pulpFiction = new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 10);
        assertEquals("Pulp Fiction", pulpFiction.getName());
        assertEquals(1994, pulpFiction.getYear());
        assertEquals("Quentin Tarantino", pulpFiction.getDirector());
        assertEquals(10, pulpFiction.getRating());
    }

    @Test
    public void shouldMoviesBeEqual(){
        assertTrue(schoolRock.equals(new Movie("School of Rock", 2003, "Richard Linklater", 0)));
    }

    @Test
    public void shouldMoviesNotBeEqual(){
        assertFalse(schoolRock.equals(new Movie("Pulp Fiction", 1994, "Quentin Tarantino", 10)));
    }

    @Test
    public void shouldTestInequalityWhenComparedToOtherObject(){
        Item book = new Book("Hobbit", "JRR Tolkien", 1937);
        assertFalse(schoolRock.equals(book));
    }

    @Test
    public void shouldNullMovieNotBeEqual(){
        assertFalse(schoolRock.equals(null));
    }

    @Test
    public void shouldReturnUnratedRatingToString(){
        String expectedMessage = "unrated";
        String actualMessage = Movie.convertRatingToString(0);

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldPrintMovieDetails(){
        String expectedMessage = "School of Rock (2003), Richard Linklater, unrated";

        String actualMessage = schoolRock.printDetails();

        assertEquals(expectedMessage, actualMessage);
    }

}
