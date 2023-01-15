package org.example.util;


import java.time.LocalDateTime;
import java.util.UUID;

public class Log {
    private static int allPostCount=0;
    private String logId;
    private LocalDateTime timestamp;
    private String msg;
    private User author;

    public Log(String msg, User author){
        this.msg=msg;
        this.author=author;
        timestamp = LocalDateTime.now();
        logId= UUID.randomUUID().toString();
        allPostCount++;
    }

    public String getLogId() {
        return logId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public User getAuthor() {
        return author;
    }
}

