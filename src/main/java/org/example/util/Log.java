package org.example.util;


import java.time.LocalDateTime;
import java.util.UUID;

public class Log {
    private String logId;
    private LocalDateTime timestamp;
    private String msg;
    private User author;
    public Log(){
        //for Object Mapper
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

