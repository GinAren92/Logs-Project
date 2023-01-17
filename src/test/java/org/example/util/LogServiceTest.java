package org.example.util;

import org.example.cache.LogHolder;

import static org.junit.jupiter.api.Assertions.*;

class LogServiceTest {
    private final LogService objectUnderTest = new LogService();

    @org.junit.jupiter.api.Test
    void checkAccesLvlIfShouldBeTrue() {
        //given
        User user1 = new User("user1@user.pl", "User!1","user");
        User user2 = new User("user2@user.pl", "User!1","user");
        Log log = new Log("",user1);
        //when
        boolean acces = LogService.checkAcces(log,user2);
        //then
        assertTrue(acces);
    }
    @org.junit.jupiter.api.Test
    void checkAccesLvlShouldReturnTrueForAdmin() {
        //given
        User user1 = new User("user1@user.pl", "User!1","user");
        User user2 = new User("user2@user.pl", "User!1","admin");
        Log log = new Log("",user1);
        //when
        boolean acces = LogService.checkAcces(log,user2);
        //then
        assertTrue(acces);
    }
    @org.junit.jupiter.api.Test
    void checkAccesLvlShouldBeFalseForUser() {
        //given
        User user1 = new User("user1@user.pl", "User!1","admin");
        User user2 = new User("user2@user.pl", "User!1","user");
        Log log = new Log("",user1);
        //when
        boolean acces = LogService.checkAcces(log,user2);
        //then
        assertFalse(acces);
    }

}