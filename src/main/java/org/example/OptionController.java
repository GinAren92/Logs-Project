package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OptionController {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private UsersHolder usersHolder = new UsersHolder();
    private LogHolder logHolder = new LogHolder();

    public void getStarted() {
        while (true) {
            String option = "";
            System.out.println("Do you want to review saved Logs or create a new one? review/save");

            try {
                option = reader.readLine();
                usersHolder.loging(reader);

                if (option.equals("review")) {
                    logHolder.allLogReader(usersHolder.getActualUser());
                    if (usersHolder.getActualUser().getAccesLevel().equals("admin")) {
                        System.out.println("Czy chciałbyś któryś Log usunąć? y/n");
                        if (reader.readLine().equals("y")) {
                            System.out.println("Wpisz ID loga który usuwasz:");
                            logHolder.logRemover(reader.readLine());
                        }
                    }
                } else if (option.equals("save")) {
                    System.out.println("Enter the contents of the Log below: ");
                    Log log = new Log(reader.readLine(), usersHolder.getActualUser());
                    logHolder.addLog(log);
                }
                System.out.println("Do you want to continue the program? y/n");
                if(reader.readLine().equals("n")) break;
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
