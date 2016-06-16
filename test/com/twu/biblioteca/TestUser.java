package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by droman on 6/15/16.
 */
public class TestUser {

    private PersonInfo davidInfo;
    private User david;

    @Before
    public void setUp(){
        davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");
        david = new User(davidInfo, "111-1111", "biblioteca1");
    }

    @Test
    public void shouldInitializeUser(){
        assertEquals( david.getPersonalInformation().getName(), "David Roman");
        assertEquals( david.getPersonalInformation().getEmail(), "droman@thoughtworks.com");
        assertEquals( david.getPersonalInformation().getPhone(), "0939053446");
        assertEquals( david.getUserID(), "111-1111");
        assertEquals( david.getPassword(), "biblioteca1");
    }

    @Test
    public void shouldPrintUserDetails() {
        String expectedMessage = "Name: David Roman\n" +
                                 "Email: droman@thoughtworks.com\n" +
                                 "Phone: 0939053446\n" +
                                 "User ID: 111-1111\n";

        String actualMessage = david.printUserDetails();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldTestValidPassword(){
        assertTrue(david.checkPassword("biblioteca1"));
    }

    @Test
    public void shouldTestInvalidPassword(){
        assertFalse(david.checkPassword("bascasliotsdfs"));
    }

    @Test
    public void shouldTestNullPassword(){
        assertFalse(david.checkPassword("bascasliotsdfs"));
    }

    @Test
    public void shouldUsersBeEqual() {
        assertTrue(david.equals(new User(davidInfo, "111-1111", "biblioteca1")));
    }

    @Test
    public void shouldUsersNotBeEqual() {
        assertFalse(david.equals(new User(davidInfo, "121-1122", "biblioteca1")));;
    }
}
