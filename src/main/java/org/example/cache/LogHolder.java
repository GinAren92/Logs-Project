package org.example.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.util.Log;
import org.example.util.LogService;
import org.example.util.User;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LogHolder {
    private Map<String, Log> listOfLogs = new HashMap<>();
    private final LogService logService = new LogService();

    public void addLog(Log log){
        listOfLogs.put(log.getLogId(), log);
    }

    public void allLogReader(User actualUser){
        logService.allLogReader(actualUser,listOfLogs);
    }

    public boolean checkAccesLvl(Log log,User actualUser){
        return logService.checkAccesLvl(log,actualUser);
    }
    public void logRemover(String id){
        listOfLogs.remove(id);
    }
}
