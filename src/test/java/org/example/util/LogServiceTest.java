package org.example.util;

import org.example.cache.LogHolder;

import static org.junit.jupiter.api.Assertions.*;

class LogServiceTest {
    private final LogService objectUnderTest = new LogService();

    @org.junit.jupiter.api.Test
    void allLogReaderIfShouldBeFalse() {
        //given
        User user = new User("test1@domain.pl","Test1!","user");
        User admin = new User("test2@domain.pl","Test1!","admin");
        LogHolder logHolder = new LogHolder();
        Log log = new Log("JUnit test msg",admin);
        //when
        logHolder.addLog(log);
        User actualUser = user;
        //then
    }
    @org.junit.jupiter.api.Test
    void allLogReaderIfShouldBeTrue() {
    }

    @org.junit.jupiter.api.Test
    void checkAccesLvl() {
    }
}