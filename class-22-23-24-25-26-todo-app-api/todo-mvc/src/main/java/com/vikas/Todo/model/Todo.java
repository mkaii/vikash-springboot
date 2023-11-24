package com.vikas.Todo.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class Todo {
    private Integer id;
    private String content;
    private boolean done;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime dueDateTime;
    private LocalDateTime doneTimeStamp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public LocalDateTime getDoneTimeStamp() {
        return doneTimeStamp;
    }

    public void setDoneTimeStamp(LocalDateTime doneTimeStamp) {
        this.doneTimeStamp = doneTimeStamp;
    }
}
