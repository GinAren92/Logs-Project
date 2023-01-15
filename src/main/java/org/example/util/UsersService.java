package org.example.util;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersService {
    private static final ObjectMapper objMapper = new ObjectMapper();
    private static final ObjectWriter objWriter = objMapper.writer(new DefaultPrettyPrinter());
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objMapper.registerModule(javaTimeModule);
        objMapper.setDateFormat(simpleDateFormat);
    }

    public List<User> readFromFile(){
        List<User> usersFromFile = new ArrayList<>();
        File users = new File("users.json");
        if(users.exists()){
            try {
                usersFromFile = Arrays.asList(objMapper.readValue(Paths.get("users.json").toFile(), User[].class));
            } catch (IOException e) {
                throw new RuntimeException("Metoda wczytywania z pliku Users");
            }
        }

        return usersFromFile;
    }
    public void writeToFile(List<User> listOfUsers){
          File users = new File("users.json");
          if(users.exists()){
            try {
                objWriter.writeValue(Paths.get("users.json").toFile(),listOfUsers);
            } catch (IOException e) {
                throw new RuntimeException("Metoda zapisywania Userów");
            }
          }
    }
    public User loging(BufferedReader reader, List<User> listOfUsers) {
        User actualUser = null;
        while(actualUser==null) {
            System.out.println("Login: ");
            String login = "", password = "";
            try {
                login = reader.readLine();
                System.out.println("Password: ");
                password = reader.readLine();
            } catch (Exception e) {
                e.getMessage();
            }
            for (User u :
                    listOfUsers) {
                if (u.getLogin().equals(login) && u.getPassword().equals(password))
                    actualUser = u;
            }
            if(actualUser==null) System.out.println("Incorrect login details");
        }
        return actualUser;
    }
}
