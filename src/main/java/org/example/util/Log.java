package org.example.util;


import java.time.LocalDateTime;
import java.util.UUID;

public class Log {
    private final String logId;
    private final LocalDateTime timestamp;
    private final String msg;
    private final User author;
    public Log(){
        //for Object Mapper
        author = null;
        logId = null;
        timestamp = null;
        msg = null;
    }

    public Log(String msg, User author){
        this.msg=msg;
        this.author=author;
        timestamp = LocalDateTime.now();
        logId= UUID.randomUUID().toString();
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

