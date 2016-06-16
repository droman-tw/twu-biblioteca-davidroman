package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by droman on 6/15/16.
 */
public class TestPersonInfo {

    private PersonInfo davidInfo;

    @Before
    public void setUp(){
        davidInfo = new PersonInfo("David Roman", "droman@thoughtworks.com", "0939053446");
    }

    @Test
    public void shouldInitiliazePersonInfoObject(){
        assertEquals(davidInfo.getName(), "David Roman");
        assertEquals(davidInfo.getEmail(), "droman@thoughtworks.com");
        assertEquals(davidInfo.getPhone(), "0939053446");
    }

    @Test
    public void shouldPrintPersonalInformation(){
        String expectedMessage = "Name: David Roman\n"+
                                 "Email: droman@thoughtworks.com\n"+
                                 "Phone: 0939053446\n";

        String actualMessage = davidInfo.printPersonalInformation();

        assertEquals(expectedMessage, actualMessage);

    }

}
