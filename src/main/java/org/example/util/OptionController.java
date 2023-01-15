package org.example.util;

import org.example.cache.LogHolder;
import org.example.cache.UsersHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OptionController {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private UsersHolder usersHolder = new UsersHolder();
    private LogHolder logHolder = new LogHolder();

    public void getStarted() {
        boolean flag = true;
        while (flag) {
            String option = "";
            System.out.println("Do you want to review saved Logs or create a new one? review/save");

            try {
                option = reader.readLine();
                usersHolder.loging(reader);

                if (option.equals("review")) {
                    logHolder.allLogReader(usersHolder.getActualUser());
                    if (usersHolder.getActualUser().getAccesLevel().equals("admin")) {
                        System.out.println("Would you like any of the Logs removed? y/n");
                        if (reader.readLine().equals("y")) {
                            System.out.println("Enter the ID of the log you are deleting:");
                            logHolder.logRemover(reader.readLine());
                        }
                    }
                } else if (option.equals("save")) {
                    System.out.println("Enter the contents of the Log below: ");
                    Log log = new Log(reader.readLine(), usersHolder.getActualUser());
                    logHolder.addLog(log);
                }
                System.out.println("Do you want to continue the program? y/n");
                if(reader.readLine().equals("n")) {
                    flag = false;
                    logHolder.saveToFile();
                    usersHolder.saveUsersToFile();

                }
            } catch (Exception e) {
                e.getMessage();
            }

        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
