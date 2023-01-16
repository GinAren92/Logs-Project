package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogService {
    private static final ObjectMapper objMapper = new ObjectMapper();
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objMapper.registerModule(javaTimeModule);
        objMapper.setDateFormat(simpleDateFormat);
    }

    public Map<String,Log> readFromFile() {
        Map<String, Object> tmpMap = new HashMap<>();
        Map<String, Log> listOfLogs = new HashMap<>();
        File file = new File("logs.json");
        if (file.exists()) {
            try {
                tmpMap = objMapper.readValue(new File("logs.json"), Map.class);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            tmpMap.forEach((key, val) -> {
                String jsonVal = null;
                try {
                    jsonVal = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(val);
                    Log log = objMapper.readValue(jsonVal, Log.class);
                    listOfLogs.put(key, log);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        }
            return listOfLogs;
    }
    public void writeToFile(Map<String,Log> listOfLogs){
        try {
            objMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("logs.json").toFile(),listOfLogs);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void allLogReader(User actualUser, Map<String,Log> listOfLogs){

        listOfLogs.forEach((key,value)->{
            if(checkAcces(value, actualUser)) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.println("Log ID: "+key);
                System.out.println("Date of creation: "+value.getTimestamp().format(format));
                System.out.println("The message: "+value.getMsg());
                System.out.println("Author: "+value.getAuthor().getLogin());
                System.out.println("___________________________________________________________");
            }
        });
    }

    public boolean checkAcces(Log log,User actualUser){
        if(actualUser.getAccesLevel().equals(log.getAuthor().getAccesLevel()))
            return true;
        else if(actualUser.getAccesLevel().equals("admin"))
            return true;
        return false;
    }
}
