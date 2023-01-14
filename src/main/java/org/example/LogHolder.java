package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LogHolder {
    private Map<String,Log> listOfLogs = new HashMap<>();
    private static final ObjectMapper objMapper = new ObjectMapper();
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objMapper.registerModule(javaTimeModule);
        objMapper.setDateFormat(simpleDateFormat);
    }
    public void addLog(Log log){
        listOfLogs.put(log.getLogId(), log);
    }

    public void allLogReader(User actualUser){

        listOfLogs.forEach((key,value)->{
            if(checkAccesLvl(value, actualUser)) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.println("Log ID: "+key);
                System.out.println("Data utworzenia: "+value.getTimestamp().format(format));
                System.out.println("Wiadomość: "+value.getMsg());
                System.out.println("Autor: "+value.getAuthor().getLogin());
            }
        });
        }

    public boolean checkAccesLvl(Log log,User actualUser){
        if(actualUser.getAccesLevel().equals(log.getAuthor().getAccesLevel()))
            return true;
        else if(actualUser.getAccesLevel().equals("admin"))
            return true;
        return false;
    }
    public void logRemover(String id){
        listOfLogs.remove(id);
    }
}
