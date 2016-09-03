package com.example.jyotirmay.utilities.todo;

import android.support.v7.widget.AppCompatCheckBox;

import java.util.ArrayList;

/**
 * Created by jyotirmay on 28-08-2016.
 */
public class tasks {

    public static class task{
        String TaskName;
        String TaskDate;

        public task(String taskName, String taskDate) {
            TaskName = taskName;
            TaskDate = taskDate;
        }
    }
    public static ArrayList<task> getTasks(){
        ArrayList<task> t = new ArrayList<task>();
        return t;
    }
}
