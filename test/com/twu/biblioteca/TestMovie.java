package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
