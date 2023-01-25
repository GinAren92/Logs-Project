package org.example.service;

import org.example.util.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {
private final UsersService objUnderTest = new UsersService();

    @Test
    void writeToFile() {
        //given
        List<User> userListTest = new ArrayList<>();
        User userTest = new User("test1@test.pl","Test1!","user");
        //when
        userListTest.add(userTest);
        //then
        assertDoesNotThrow(()->{
            objUnderTest.writeToFile(userListTest);
        });
    }
}