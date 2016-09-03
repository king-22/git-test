package com.example.jyotirmay.utilities.todo.model;

/**
 * Created by jyotirmay on 25-08-2016.
 */

public class Task {
    String name;
    String date;
    int done;

    public Task() {

    }

    public Task(String date, String name, int done) {
        this.name = name;
        this.date = date;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getDone() {
        return done;
    }
}



