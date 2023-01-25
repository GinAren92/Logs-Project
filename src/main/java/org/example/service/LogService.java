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
            AtomicBoolean attemptionReadingCondition = new AtomicBoolean(false);
            int attemptionReadingLoopVal = 0;
            do {
                try {
                    tmpMap = OBJECT_MAPPER.readValue(new File("logs.json"), Map.class);
                } catch (IOException e) {
                    if(attemptionReadingLoopVal<=10) attemptionReadingCondition.set(true);
                    else throw new Error(e.getCause());
                }

                int finalAttemptionReadingLoopVal = attemptionReadingLoopVal;
                tmpMap.forEach((key, val) -> {
                    String jsonVal;
                    try {
                        jsonVal = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(val);
                        Log log = OBJECT_MAPPER.readValue(jsonVal, Log.class);
                        listOfLogs.put(key, log);
                    } catch (JsonProcessingException e) {
                        if((finalAttemptionReadingLoopVal <= 10 && attemptionReadingCondition.get()) == false) attemptionReadingCondition.set(true);
                        else throw new Error(e.getCause());
                    }
                });
                if(attemptionReadingLoopVal>10){
                    attemptionReadingCondition.set(false);
                    throw new Error("Exceeded 10 failed attempts to load the file.");
                }else attemptionReadingLoopVal++;
            }while(attemptionReadingCondition.get());
        }
            return listOfLogs;
    }
    public void writeToFile(Map<String,Log> listOfLogs){
        AtomicBoolean attemptionWritingCondition = new AtomicBoolean(false);
        int attemptionWritingLoopVal = 0;
        do {
            try {
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(Paths.get("logs.json").toFile(), listOfLogs);
            } catch (IOException e) {
                if(attemptionWritingLoopVal<=10) attemptionWritingCondition.set(true);
                else throw new Error(e.getCause());
            }
            if(attemptionWritingLoopVal>10){
                attemptionWritingCondition.set(false);
                throw new Error("Exceeded 10 failed attempts to load the file.");
            }else attemptionWritingLoopVal++;
        }while(attemptionWritingCondition.get());
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
