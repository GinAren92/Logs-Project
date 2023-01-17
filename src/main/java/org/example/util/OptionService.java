package org.example.util;

import org.example.cache.LogHolder;
import org.example.cache.UsersHolder;

import java.io.BufferedReader;
import java.io.IOException;

public class OptionService {

    static void reviewOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader){
        logHolder.allLogReader(usersHolder.getActualUser());
        if (usersHolder.getActualUser().getAccesLevel().equals("admin")) {
            System.out.println("Would you like any of the Logs removed? y/n");
            try {
                if (reader.readLine().equals("y")) {
                    System.out.println("Enter the ID of the log you are deleting:");
                    logHolder.logRemover(reader.readLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void saveOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader){
        System.out.println("Enter the contents of the Log below: ");
        Log log = null;
        try {
            log = new Log(reader.readLine(), usersHolder.getActualUser());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logHolder.addLog(log);
    }

    static boolean exitOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader) {
        System.out.println("Do you want to continue the program? y/n");
        try {
            if (reader.readLine().equals("n")) {
                logHolder.saveToFile();
                usersHolder.saveUsersToFile();
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
