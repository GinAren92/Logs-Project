package org.example;

public class User {
    private String login;
    private String password;
    private String accesLevel;

    public User(String login, String password, String accesLevel){
        this.accesLevel=accesLevel;
        this.login=login;
        this.password=password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAccesLevel() {
        return accesLevel;
    }
}
