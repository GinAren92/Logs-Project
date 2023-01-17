package org.example.util;

import org.example.cache.LogHolder;
import org.example.cache.UsersHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OptionController {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final UsersHolder usersHolder = new UsersHolder();
    private final LogHolder logHolder = new LogHolder();

    public void getStarted() {
        boolean flag = true;
        while (flag) {
            String option = "";
            System.out.println("Do you want to review saved Logs or create a new one? review/save");
            try {
                option = reader.readLine();
            } catch (Exception e) {
                e.getMessage();
            }
                usersHolder.loging(reader);
                if (option.equals("review")) {
                    OptionService.reviewOption(logHolder,usersHolder,reader);
                } else if (option.equals("save")) {
                    OptionService.saveOption(logHolder,usersHolder,reader);
                }
                flag = OptionService.exitOption(logHolder,usersHolder,reader);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
