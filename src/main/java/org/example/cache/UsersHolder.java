package org.example.cache;

import org.example.util.User;
import org.example.util.UsersService;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class UsersHolder {
    private List<User> listOfUsers;
    private final UsersService usersService = new UsersService();
    private User actualUser = null;

    public UsersHolder(){
        listOfUsers = usersService.readFromFile();
        if(listOfUsers.isEmpty()){
            listOfUsers.add(new User("admin@admin.pl","Admin!1","admin"));
            listOfUsers.add(new User("user1@user.com","User#!1","user"));
        }
    }
    public User getActualUser(){
        return actualUser;
    }
    public void loging(BufferedReader reader) {
        this.actualUser = usersService.logging(reader,listOfUsers);
    }
    public void saveUsersToFile(){
        usersService.writeToFile(listOfUsers);
    }
}
