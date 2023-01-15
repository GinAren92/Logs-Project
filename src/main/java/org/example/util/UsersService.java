package org.example.util;

import java.io.BufferedReader;
import java.util.List;

public class UsersService {


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
