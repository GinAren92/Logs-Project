package org.example.service;

import org.example.util.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserValidation {
    public static String[] getLogInDetail(BufferedReader reader){
        System.out.println("Login: ");
        String login = "", password = "";
        try {
            login = reader.readLine();
            System.out.println("Password: ");
            password = reader.readLine();
        } catch (IOException e) {
            throw new Error(e);
        }
        return new String[]{login,password};
    }

    public static User searchOnUsersList(List<User> listOfUsers, String login, String password){
        for (User user :
                listOfUsers) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return user;
        }
        User errorUser = null;
        return errorUser;
    }
}
