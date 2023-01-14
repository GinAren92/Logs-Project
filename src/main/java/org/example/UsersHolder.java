package org.example;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class UsersHolder {
    private List<User> listOfUsers = new ArrayList<>();
    private User actualUser=null;

    public UsersHolder(){
        listOfUsers.add(new User("admin","admin","admin"));
        listOfUsers.add(new User("user","user","user"));
    }
    public User getActualUser(){
        return actualUser;
    }
    public void loging(BufferedReader reader) {
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
    }
}
