package org.example.service;

import org.example.util.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    private final UserValidation ojectUnderTest= new UserValidation();
    @Test
    void searchOnUsersListFirstShouldReturnNull() {
        //given
        List<User> userListTest = new ArrayList<>();
        User userTest = new User("test1@test.pl","Test1!","user");
        userListTest.add(userTest);
        String login = "test";
        String password = "test";
        //when
        User result = UserValidation.searchOnUsersList(userListTest,login,password);
        //then
        assertTrue(result==null);
        assertNull(result);
    }
    @Test
    void searchOnUsersListSecondShouldReturnNull() {
        //given
        List<User> userListTest = new ArrayList<>();
        User userTest = new User("test1@test.pl","Test1!","user");
        userListTest.add(userTest);
        String login = "test1@test.pl";
        String password = "test";
        //when
        User result = UserValidation.searchOnUsersList(userListTest,login,password);
        //then
        assertTrue(result==null);
        assertNull(result);
    }
    @Test
    void searchOnUsersListThirdShouldReturnNull() {
        //given
        List<User> userListTest = new ArrayList<>();
        User userTest = new User("test1@test.pl","Test1!","user");
        userListTest.add(userTest);
        String login = "test";
        String password = "Test1!";
        //when
        User result = UserValidation.searchOnUsersList(userListTest,login,password);
        //then
        assertTrue(result==null);
        assertNull(result);
    }
    @Test
    void searchOnUsersListFourthShouldReturnUserFromList() {
        //given
        List<User> userListTest = new ArrayList<>();
        User userTest = new User("test1@test.pl","Test1!","user");
        userListTest.add(userTest);
        String login = "test1@test.pl";
        String password = "Test1!";
        //when
        User result = UserValidation.searchOnUsersList(userListTest,login,password);
        //then
        assertFalse(result==null);
        assertNotNull(result);
    }
}