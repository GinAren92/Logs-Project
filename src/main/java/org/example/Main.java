package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UsersHolder usersHolder = new UsersHolder();
        LogHolder logHolder = new LogHolder();
        String option="";
        System.out.println("Do you want to review saved Logs or create a new one? review/save");

        try {
            option = reader.readLine();
            usersHolder.loging(reader);

            if(option.equals("review")){
                logHolder.allLogReader(usersHolder.getActualUser());
                if(usersHolder.getActualUser().getAccesLevel().equals("admin")){
                    System.out.println("Czy chciałbyś któryś Log usunąć? y/n");
                    if(reader.readLine().equals("y")){
                        System.out.println("Wpisz ID loga który usuwasz:");
                        logHolder.logRemover(reader.readLine());
                    }}
            }




            reader.close();
        }catch(Exception e){
            e.getMessage();
        }

    }

}