package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.util.Log;
import org.example.util.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LogService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.setDateFormat(simpleDateFormat);
    }

    public Map<String, Log> readFromFile() {
        Map<String, Object> tmpMap = null;
        Map<String, Log> listOfLogs = new HashMap<>();
        File file = new File("logs.json");
        if (file.exists()) {
                try {
                    tmpMap = OBJECT_MAPPER.readValue(new File("logs.json"), Map.class);
                } catch (IOException e) {
                    throw new Error(e);
                }
                tmpMap.forEach((key, val) -> {
                    String jsonVal;
                    try {
                        jsonVal = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(val);
                        Log log = OBJECT_MAPPER.readValue(jsonVal, Log.class);
                        listOfLogs.put(key, log);
                    } catch (JsonProcessingException e) {
                        throw new Error(e);
                    }
                });
        }
            return listOfLogs;
    }
    public void writeToFile(Map<String,Log> listOfLogs){
            try {
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(Paths.get("logs.json").toFile(), listOfLogs);
            } catch (IOException e) {
                throw new Error(e);
            }
    }

    public void allLogReader(User actualUser, Map<String,Log> listOfLogs){
        LogsPrinter.allLogReader(actualUser,listOfLogs);
    }

    public static boolean checkAcces(Log log,User actualUser){
        if(actualUser.getAccesLevel().equals(log.getAuthor().getAccesLevel()))
            return true;
        else if(actualUser.getAccesLevel().equals("admin"))
            return true;
        return false;
    }
}
