package org.example.service;

import org.example.util.Log;
import org.example.util.User;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LogsPrinter {
    public static void allLogReader(User actualUser, Map<String, Log> listOfLogs){
        try {
            listOfLogs.forEach((key, value) -> {
                if (LogService.checkAcces(value, actualUser)) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    System.out.println("Log ID: " + key);
                    System.out.println("Date of creation: " + value.getTimestamp().format(format));
                    System.out.println("The message: " + value.getMsg());
                    System.out.println("Author: " + value.getAuthor().getLogin());
                    System.out.println("___________________________________________________________");
                }
            });
        }catch (Exception e){
            throw new Error(e);
        }
    }
}
