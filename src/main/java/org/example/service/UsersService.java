package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.util.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.setDateFormat(simpleDateFormat);
    }

    public List<User> readFromFile(){
        List<User> usersFromFile = new ArrayList<>();
        File users = new File("users.json");
        if(users.exists()){
            try {
                usersFromFile = Arrays.asList(OBJECT_MAPPER.readValue(Paths.get("users.json").toFile(), User[].class));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return usersFromFile;
    }
    public void writeToFile(List<User> listOfUsers){
            try {
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(Paths.get("users.json").toFile(),listOfUsers);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
    }
    public User logging(BufferedReader reader, List<User> listOfUsers) {
        User actualUser = null;
        while(actualUser==null) {
            String[] loginAndPassword = UserValidation.getLogInDetail(reader);
            actualUser = UserValidation.searchOnUsersList(listOfUsers,loginAndPassword[0],loginAndPassword[1]);
            if(actualUser==null) System.out.println("Incorrect login details");
        }
        return actualUser;
    }
}
