package com.example.jyotirmay.utilities;

import java.util.ArrayList;

/**
 * Created by jyotirmay on 11-07-2016.
 */
public class Utils {
    public static class Util {
        String util_name;
        int img_id;

        public Util(String util_name, int img_id) {
            this.util_name = util_name;
            this.img_id = img_id;
        }
    }


    public static ArrayList<Util> getUtils() {
        ArrayList<Utils.Util> UTIL = new ArrayList<>(9);
        UTIL.add(new Util("Calculator",R.drawable.calculator_min));
        UTIL.add(new Util("Stopwatch",R.drawable.stopwatch));
        UTIL.add(new Util("AlarmClock",R.drawable.alarm_min));
        UTIL.add(new Util("To-Do List",R.drawable.todo_min));
        UTIL.add(new Util("Notepad",R.drawable.ntpad));
        UTIL.add(new Util("Flashlight",R.drawable.flashlight_min));
        UTIL.add(new Util("Calendar",R.drawable.calender_min));
        return UTIL;

    }


}
