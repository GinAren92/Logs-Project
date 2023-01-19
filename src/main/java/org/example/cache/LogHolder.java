package org.example.cache;

import org.example.util.Log;
import org.example.service.LogService;
import org.example.util.User;

import java.util.Map;

public class LogHolder {
    private final Map<String, Log> listOfLogs;
    private final LogService logService = new LogService();

    public LogHolder(){
        listOfLogs = logService.readFromFile();
    }

    public void addLog(Log log){
        listOfLogs.put(log.getLogId(), log);
    }

    public void allLogReader(User actualUser){
        logService.allLogReader(actualUser,listOfLogs);
    }

    public void logRemover(String id){
        listOfLogs.remove(id);
    }
    public void saveToFile(){
        logService.writeToFile(listOfLogs);
    }
}
