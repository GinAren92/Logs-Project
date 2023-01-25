package org.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final User objUnderTest = new User();
    @Test
    void checkLoginShouldReturnTrue() {
        //given
        final String testLogin1 = "5char@domain.pl";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertTrue(result);
    }
    @Test
    void checkLoginShouldReturnTrueSecond() {
        //given
        final String testLogin2 = "5char@domain.com";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin2);
        //then
        assertTrue(result);
    }
    @Test
    void checkLoginShouldReturnFalse() {
        //given less than 5 char before @
        final String testLogin1 = "to5c@domain.pl";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }
    @Test
    void checkLoginShouldReturnFalseSecond() {
        //given login without @
        final String testLogin1 = "5charDomain.pl";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }
    @Test
    void checkLoginShouldReturnFalseThird() {
        //given login without dot
        final String testLogin1 = "5char@domainpl";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }
    @Test
    void checkLoginShouldReturnFalseFourth() {
        //given login with different ending
        final String testLogin1 = "5char@domain.org";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }
    @Test
    void checkLoginShouldReturnFalseFifth() {
        //given login with multi dots
        final String testLogin1 = "5char@domain.org.pl";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }
    @Test
    void checkLoginShouldReturnFalseSixth() {
        //given login with nothing after @
        final String testLogin1 = "5char@";
        boolean result;
        //when
        result = objUnderTest.checkLogin(testLogin1);
        //then
        assertFalse(result);
    }

    @Test
    void checkPasswordTrue() {
        //given password with 1 Upper Case letter, 1 numeric, 1 special char
        final String testPassword = "Test1!";
        boolean result;
        //when
        result = objUnderTest.checkPassword(testPassword);
        //then
        assertTrue(result);
    }
    @Test
    void checkPasswordFalse() {
        //given password without 1 Upper Case letter
        final String testPassword = "test1!";
        boolean result;
        //when
        result = objUnderTest.checkPassword(testPassword);
        //then
        assertFalse(result);
    }
    @Test
    void checkPasswordFalseSecond() {
        //given password without any numeric char
        final String testPassword = "Test!";
        boolean result;
        //when
        result = objUnderTest.checkPassword(testPassword);
        //then
        assertFalse(result);
    }
    @Test
    void checkPasswordFalseThird() {
        //given password without any special char
        final String testPassword = "Test1";
        boolean result;
        //when
        result = objUnderTest.checkPassword(testPassword);
        //then
        assertFalse(result);
    }
    @Test
    void checkPasswordFalseFourth() {
        //given password without any requirements
        final String testPassword = "test";
        boolean result;
        //when
        result = objUnderTest.checkPassword(testPassword);
        //then
        assertFalse(result);
    }


}