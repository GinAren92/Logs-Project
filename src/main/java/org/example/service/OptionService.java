package org.example.service;

import org.example.cache.LogHolder;
import org.example.cache.UsersHolder;
import org.example.util.Log;
import org.example.util.MyVoidException;

import java.io.BufferedReader;
import java.io.IOException;

public class OptionService {

    public static void reviewOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader){
        logHolder.allLogReader(usersHolder.getActualUser());
        if (usersHolder.getActualUser().getAccesLevel().equals("admin")) {
            removeLog(logHolder,reader);
        }
    }

    public static void saveOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader){
        System.out.println("Enter the contents of the Log below: ");
        Log log = null;
        try {
            log = new Log(reader.readLine(), usersHolder.getActualUser());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logHolder.addLog(log);
    }

    public static boolean exitOption(LogHolder logHolder, UsersHolder usersHolder, BufferedReader reader) {
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
    static private void removeLog(LogHolder logHolder, BufferedReader reader){
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
