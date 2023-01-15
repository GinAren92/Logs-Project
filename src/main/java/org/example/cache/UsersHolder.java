package org.example.cache;

import org.example.util.User;
import org.example.util.UsersService;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class UsersHolder {
    private List<User> listOfUsers = new ArrayList<>();
    private final UsersService usersService = new UsersService();
    private User actualUser = null;

    public UsersHolder(){
        listOfUsers.add(new User("admin","admin","admin"));
        listOfUsers.add(new User("user","user","user"));
    }
    public User getActualUser(){
        return actualUser;
    }
    public void loging(BufferedReader reader) {
        this.actualUser = usersService.loging(reader,listOfUsers);
    }
}
