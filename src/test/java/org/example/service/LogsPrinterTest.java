package org.example.service;

import org.example.util.Log;
import org.example.util.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogsPrinterTest {

    @Test
    void allLogReader() {
        //given
        User user1 = new User("user1@user.pl", "User!1","user");
        Log log1 = new Log("Test 1 Printer",user1);
        Log log2 = new Log("Test 2 Printer",user1);
        Map<String,Log> testLogHolder = new HashMap<>();
        //when
        testLogHolder.put(log1.getLogId(),log1);
        testLogHolder.put(log2.getLogId(), log2);
        //then
        assertDoesNotThrow(()->{
            LogsPrinter.allLogReader(user1,testLogHolder);
        });

    }
}