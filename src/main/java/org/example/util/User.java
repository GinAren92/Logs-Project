package org.example.util;

public class User {
    private String login;
    private String password;
    private String accesLevel;

    public User (){
        // for Object Mapper
    }
    public User(String login, String password, String accesLevel){
        this.accesLevel=accesLevel;
        if(checkLogin(login)){
            this.login=login;
        }else throw new IllegalArgumentException("Login should be the same format like email adres ex. atLeast5Char@domain.com or ..@domain.pl");
        if(checkPasword(password)){
            this.password=password;
        }else throw new IllegalArgumentException("Password has to contain at least: one special character, one upper case letter and one number.");
    }

    private boolean checkLogin(String login){
        if(login.contains("@")){
            String[] splitedLogin = login.split("@");
            if(splitedLogin[0].length()>=5){
                String[] afterAtSymbol = splitedLogin[1].split("\\.");
                if(afterAtSymbol.length<3){
                    if(afterAtSymbol[1].equals("pl") || afterAtSymbol[1].equals("com")) return true;
                }
            }
        }
        return false;
    }
    private boolean checkPasword(String password){
        char[] tmpCharArray = password.toCharArray();
        int upperCase = 0, specialChar = 0, numberChar = 0;
        for(char c : tmpCharArray){
            int cAcsii = c;
            if((cAcsii>=33 && cAcsii<=47) || (cAcsii>=58 && cAcsii<=64) || (cAcsii>=91 && cAcsii<=96) || (cAcsii>=123 && cAcsii<=126)) specialChar++;
            if(cAcsii>=65 && cAcsii<=90) upperCase++;
            if(cAcsii>=48 && cAcsii<=57) numberChar++;
            if(upperCase>0 && specialChar>0 && numberChar>0) return true;
        }
        return false;
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
